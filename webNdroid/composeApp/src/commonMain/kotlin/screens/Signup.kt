package screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import Login_Signup.CredentialsBox
import Login_Signup.PageTitle
import models.createResponsiveUi
import org.jetbrains.compose.ui.tooling.preview.Preview


@Preview
@Composable
fun SignUp() {
    BoxWithConstraints() {
        val screenWidth = maxWidth
        val screenHeight = maxHeight
        val ui = createResponsiveUi(screenWidth, screenHeight)
        if (ui.isMobile) {
            /* Create box size of full screen and make a half of screen sized padding to make box on bottom.*/
            Box(modifier = Modifier.fillMaxSize().background(Color(0xFFF5E1))) {
                Column(modifier = Modifier.fillMaxSize()) {
                    Box(modifier = Modifier.weight(1f)) {
                        PageTitle(
                            ui = ui,
                            title = "Sign Up"
                        )
                    }
                    //Spacer(Modifier.weight(1f))

                    Box(modifier = Modifier.weight(1f)) {
                        CredentialsBox(
                            ui = ui,
                            firstBoxTitle = "Email",
                            firstBoxHolderValue = "Enter your email address",
                            secondBoxTitle = "Password",
                            secondBoxHolderValue = "Enter password",
                            buttonText = "Sign up",
                            navigationSentence = "Already have an account?",
                            navigationHighlightSentence = "Login!",
                            isSignUp = true,
                            isLogin = false
                        )
                    }

                }

            }
        } else {
            Box(modifier = Modifier.fillMaxSize()) {
                /* Create box size of full screen and make a half of screen sized padding to make box on bottom.*/
                Box(
                    modifier = Modifier.fillMaxWidth(0.4f).fillMaxHeight()
                        .background(Color(0xFFF5E1)).align(Alignment.Center),
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Box(modifier = Modifier.weight(1f)) {
                            PageTitle(
                                ui = ui,
                                title = "Sign Up"
                            )
                        }
                        //Spacer(Modifier.weight(1f))

                        Box(modifier = Modifier.weight(1f)) {
                            CredentialsBox(
                                ui = ui,
                                firstBoxTitle = "Email",
                                firstBoxHolderValue = "Enter your email address",
                                secondBoxTitle = "Password",
                                secondBoxHolderValue = "Enter password",
                                buttonText = "Sign up",
                                navigationSentence = "Already have an account?",
                                navigationHighlightSentence = "Login!",
                                isSignUp = true,
                                isLogin = false

                            )
                        }

                    }

                }
            }
        }

    }
}
