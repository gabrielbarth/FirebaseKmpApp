import androidx.compose.runtime.Composable
import androidx.compose.material3.MaterialTheme
import cafe.adriel.voyager.navigator.Navigator
import com.app.firebase.features.login.LoginScreen

@Composable
fun AppRoot() {
    MaterialTheme {
        Navigator(screen = LoginScreen)
    }
}
