package com.example.mangary3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mangary3.core.components.DrawerContent
import com.example.mangary3.core.constants.APIConstants
import com.example.mangary3.presentation.navigation.AppBottomNavigationBar
import com.example.mangary3.presentation.navigation.NavigateScreen
import com.example.mangary3.presentation.theme.Mangary3Theme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Mangary3Theme {
                val navController = rememberNavController()
                MainApplication(navController)
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApplication(navController: NavHostController) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val routesWithAppBar = setOf(
        APIConstants.MANGA_HOME_SCREEN
    )
    val shouldShowAppBar = currentRoute in routesWithAppBar
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet(
                    modifier = Modifier.width(300.dp),
                    drawerContainerColor = MaterialTheme.colorScheme.surface
                ) {
                    DrawerContent(
                        currentRoute = currentRoute,
                        onNavigate = { route ->
                            scope.launch { drawerState.close() }
                            navController.navigate(route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        onClose = {
                            scope.launch { drawerState.close() }
                        }
                    )
                }
            },
            gesturesEnabled = drawerState.isOpen || shouldShowAppBar
        ) {
            Scaffold { padding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                ) {
                    NavigateScreen(navController = navController)
                    AppBottomNavigationBar(navController = navController)
                }
            }
        }
    }
}

//@Composable
//fun rememberWindowLayoutInfo(): WindowLayoutInfo {
//    val context = LocalContext.current
//    val windowInfoTracker = WindowInfoTracker.getOrCreate(context)
//    val lifecycleOwner = LocalLifecycleOwner.current
//
//    val windowLayoutInfoState = remember {
//        mutableStateOf(WindowLayoutInfo(emptyList()))
//    }
//
//    DisposableEffect(lifecycleOwner) {
//        val job = windowInfoTracker.windowLayoutInfo(lifecycleOwner)
//            .onEach { layoutInfo ->
//                windowLayoutInfoState.value = layoutInfo
//            }
//            .launchIn(lifecycleOwner.lifecycleScope)
//
//        onDispose { job.cancel() }
//    }
//
//    return windowLayoutInfoState.value
//}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Mangary3Theme {

    }
}