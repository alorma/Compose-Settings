package com.alorma.compose.settings.example.ui.screens

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.alorma.compose.settings.example.UserInfo
import com.alorma.compose.settings.example.demo.AppScaffold
import com.alorma.compose.settings.example.serializer.UserSerializer
import com.alorma.compose.settings.storage.base.getValue
import com.alorma.compose.settings.storage.base.setValue
import com.alorma.compose.settings.storage.datastore.proto.rememberProtoDataStoreSettingState
import com.alorma.compose.settings.storage.datastore.proto.rememberProtoDataStoreState
import com.alorma.compose.settings.storage.datastore.proto.rememberProtoDataStoreTransformSettingState
import kotlin.math.roundToInt

@Composable
fun ProtoScreen(
    navController: NavController = rememberNavController(),
) {

    val snackbarHostState = remember { SnackbarHostState() }
    var snackbarString by remember { mutableStateOf("") }

    LaunchedEffect(snackbarString) {
        if (snackbarString.isBlank()) return@LaunchedEffect

        snackbarHostState.apply {
            currentSnackbarData?.dismiss()
            showSnackbar(snackbarString)
        }
    }


    AppScaffold(
        navController = navController,
        title = { Text(text = "DataClassScreen") },
        snackbarHostState = snackbarHostState,
    ) {
        val dataStoreState = rememberProtoDataStoreState(
            filename = "proto_screen_sample.pb",
            serializer = UserSerializer
        )
        // Pattern1
        var userId1 by rememberProtoDataStoreTransformSettingState(
            protoDataStoreState = dataStoreState,
            encoder = {proto, newLoginUserId-> proto.toBuilder().setUserId1(newLoginUserId).build() },
            decoder = { it.userId1 },
        )
        var userName1 by rememberProtoDataStoreTransformSettingState(
            protoDataStoreState = dataStoreState,
            encoder = {proto, newLoginUserName -> proto.toBuilder().setUserName1(newLoginUserName).build() },
            decoder = { it.userName1 },
        )
        // Pattern2
        var userData2 by rememberProtoDataStoreTransformSettingState(
            protoDataStoreState = dataStoreState,
            encoder = {proto, newLoginUser->
                proto
                    .toBuilder()
                    .setUserId2(newLoginUser.id)
                    .setUserName2(newLoginUser.name)
                    .build()
            },
            decoder = { UserInfo(it.userId2, it.userName2 ) },
        )
        // Pattern3
        var userData by rememberProtoDataStoreSettingState(
            protoDataStoreState = dataStoreState
        )
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
        ) {
            item {
                InputIdAndData(
                    pattern = "Pattern1",
                    id = userId1,
                    name = userName1,
                ) { id, name ->
                    userId1 = id
                    userName1 = name
                    snackbarString = "Pattern1: id=$id name=$name"
                }
            }
            item { Divider() }
            item {
                InputIdAndData(
                    pattern = "Pattern2",
                    id = userData2.id,
                    name = userData2.name,
                ) { id, name ->
                    userData2 = UserInfo(id, name)
                    snackbarString = "Pattern2: id=$id name=$name"
                }
            }
            item { Divider() }
            item {
                InputIdAndData(
                    pattern = "Pattern3",
                    id = userData.userId3,
                    name = userData.userName3,
                ) { id, name ->
                    userData = userData
                        .toBuilder()
                        .setUserId3(id)
                        .setUserName3(name)
                        .build()
                    snackbarString = "Pattern3: id=$id name=$name"
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColumnScope.InputIdAndData(
    pattern: String,
    id: Int,
    name: String,
    onSave:(id: Int, name: String) -> Unit,
) {
    Text(
        modifier = Modifier.padding(16.dp, 16.dp),
        text = pattern,
        style = MaterialTheme.typography.headlineLarge
    )
    var tempName by remember(name) { mutableStateOf(name) }
    var tempId: Int? by remember(id) { mutableStateOf(id) }
    val isNameError by remember {
        derivedStateOf { tempName.isBlank() }
    }
    val isIdError by remember {
        derivedStateOf { tempId == null }
    }
    OutlinedTextField(
        modifier = Modifier
            .padding(8.dp, 20.dp)
            .fillMaxWidth(),
        value = (tempId ?: "").toString(),
        onValueChange = { tempId = it.toFloatOrNull()?.roundToInt() },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        label = { Text(text = "ID") },
        isError = isIdError
    )
    if (isIdError) {
        Text(
            text = "ID must be filled",
            color = MaterialTheme.colorScheme.error
        )
    }

    OutlinedTextField(
        modifier = Modifier
            .padding(8.dp, 20.dp)
            .fillMaxWidth(),
        value = tempName,
        onValueChange = { tempName = it },
        label = { Text(text = "Name") },
        isError = isNameError
    )
    if (isNameError) {
        Text(
            text = "Name must be filled",
            color = MaterialTheme.colorScheme.error
        )
    }

    Button(
        modifier = Modifier.padding(16.dp, 16.dp),
        enabled = tempName.isNotBlank() && tempId != null,
        onClick = { onSave((tempId ?: 0).toInt(), tempName) }
    ) {
        Text("Save")
    }

}