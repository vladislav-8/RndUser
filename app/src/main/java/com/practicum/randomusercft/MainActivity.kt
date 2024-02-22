import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.practicum.randomusercft.ui.theme.RandomUserCFTTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RandomUserCFTTheme {
             //TEST dev commit
            }
        }
    }
}