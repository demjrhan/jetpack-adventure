package textfield.learning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import textfield.learning.ui.theme.TextFieldLearningTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TextFieldLearningTheme {

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Preview() {
    TextFieldLearningTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                CustomTextFieldRefactored(background = MaterialTheme.colorScheme.primaryContainer,textColor = Color.Black)
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                CustomTextFieldRefactored(background = MaterialTheme.colorScheme.primary,textColor = Color.White)
            }
        }


    }
}

@Composable
fun SearchField(text: String, onValueChange: (String) -> Unit, color: Color) {
    val customTextSelectionColors = TextSelectionColors(
        handleColor = Color.Transparent,
        backgroundColor = Color.Transparent
    )

    CompositionLocalProvider(LocalTextSelectionColors provides customTextSelectionColors) {
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            textStyle = TextStyle(
                fontFamily = FontFamily.Monospace,
                color = color
            ),
            modifier = Modifier.fillMaxWidth(1f),
            cursorBrush = SolidColor(Color.Unspecified)
        )
    }


}

@Composable
fun SearchIcon(onSearch: () -> Unit, color: Color) {
    Icon(
        imageVector = Icons.Default.Search,
        contentDescription = "",
        modifier = Modifier.clickable() {
            onSearch()
        },
        tint = color
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DeleteIcon(visibility: Boolean, onClick: () -> Unit, color: Color) {
    AnimatedVisibility(
        visible = visibility,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Icon(
            imageVector = Icons.Default.Clear,
            contentDescription = "",
            modifier = Modifier
                .animateEnterExit(
                    enter = slideInVertically(),
                    exit = slideOutVertically()
                )
                .clickable {
                    onClick()
                },
            tint = color
        )
    }
}


@Composable
fun CustomTextFieldRefactored(
    modifier: Modifier = Modifier,
    background : Color,
    textColor: Color
) {
    var text by remember { mutableStateOf("") }
    var searchText by remember { mutableStateOf("Search") }
    var isTextVisible by remember { mutableStateOf(true) }
    var searchTextAlpha by remember {
        mutableFloatStateOf(1f)
    }
    val coroutineScope = rememberCoroutineScope();
    Box(
        modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
            .clip(RoundedCornerShape(50.dp))
            .background(background)
            .padding(15.dp)

    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {

            SearchIcon(onSearch = {
                if (text.isNotEmpty()) {
                    searchTextAlpha = 0.5f
                    text = "Searched!"
                    coroutineScope.launch {
                        delay(2000)
                        searchTextAlpha = 1f
                        text = ""

                    }
                }
            }, color = textColor)

            Spacer(modifier.padding(end = 8.dp))



            Box(modifier = modifier.weight(1f)) {
                SearchField(text = text, onValueChange = { text = it }, color = textColor)
                if (text.isEmpty()) {
                    Text(
                        text = searchText,
                        color = textColor,
                        modifier = Modifier.alpha(0.5f)
                    )
                } else {
                    searchText = ""
                    modifier.alpha(searchTextAlpha)
                }
            }

            Spacer(modifier.padding(end = 8.dp))

            DeleteIcon(visibility = text.isNotEmpty(), onClick = {

                isTextVisible = false
                coroutineScope.launch {
                    delay(100)
                    text = ""
                    isTextVisible = true
                }
            }, color = textColor)
        }
    }

}

