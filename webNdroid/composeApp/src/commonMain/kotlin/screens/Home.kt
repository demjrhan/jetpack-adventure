package screens

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import components.MobileHomeContent
import components.WebHomeContent
import kotlinx.coroutines.launch
import models.MobileScaffold
import models.WebScaffold
import models.createResponsiveUi


/*
In Android, the common Top Bar and Bottom Bar size is 56.dp.
In Web, the common Top Bar size is 60.dp.
In Web, the common Bottom Bar size is 60.dp or 64.dp.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home() {
    BoxWithConstraints() {
        val screenWidth = maxWidth
        val screenHeight = maxHeight
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val scope = rememberCoroutineScope()

        val ui = createResponsiveUi(screenWidth, screenHeight)
        if (ui.isMobile) {
            MobileScaffold(ui = ui, drawerState = drawerState, scope = scope) {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { },
                            navigationIcon = {
                                IconButton(onClick = {
                                    scope.launch { drawerState.open() }
                                }) {
                                    Icon(
                                        Icons.Default.Menu,
                                        contentDescription = "Menu",
                                        tint = ui.contrastColor
                                    )
                                }
                            },
                            colors = TopAppBarDefaults.topAppBarColors(containerColor = ui.backgroundColor),
                        )
                    },
                    content = { innerPadding ->
                        MobileHomeContent(ui = ui, innerPadding)

                    }
                )
            }

        } else {
            WebScaffold(ui = ui, drawerState = drawerState, scope = scope) {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { },
                            navigationIcon = {
                                IconButton(onClick = {
                                    scope.launch { drawerState.open() }
                                }) {
                                    Icon(
                                        Icons.Default.Menu,
                                        contentDescription = "Menu",
                                        tint = ui.contrastColor
                                    )
                                }
                            },
                            colors = TopAppBarDefaults.topAppBarColors(containerColor = ui.backgroundColor),

                            )
                    },
                    content = { innerPadding ->
                        WebHomeContent(ui = ui, innerPadding)
                    }
                )
            }
        }
    }
}
