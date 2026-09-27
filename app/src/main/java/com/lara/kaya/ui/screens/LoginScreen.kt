package com.lara.kaya.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen(
    onLoginSuccess: (email: String, isNewUser: Boolean) -> Unit
) {
    val backgroundColor = MaterialTheme.colorScheme.background
    val textColor = MaterialTheme.colorScheme.onBackground
    val surfaceColor = MaterialTheme.colorScheme.surface

    var isSignUpTab by remember { mutableStateOf(false) }
    var emailInput by remember { mutableStateOf("athlete@kaya.fit") }
    var passwordInput by remember { mutableStateOf("Arpitisking@9989") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "K A Y A",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 8.sp
                ),
                color = textColor
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "SUPABASE AUTHENTICATION",
                style = MaterialTheme.typography.labelSmall.copy(
                    fontSize = 10.sp,
                    letterSpacing = 2.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = textColor.copy(alpha = 0.5f)
            )

            Spacer(modifier = Modifier.height(36.dp))

            // Tab Selector: [ SIGN IN ] vs [ SIGN UP ]
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, textColor.copy(alpha = 0.2f)),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(if (!isSignUpTab) textColor else backgroundColor)
                        .clickable { isSignUpTab = false }
                        .padding(vertical = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "[ SIGN IN ]",
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Black, letterSpacing = 1.sp),
                        color = if (!isSignUpTab) backgroundColor else textColor
                    )
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(if (isSignUpTab) textColor else backgroundColor)
                        .clickable { isSignUpTab = true }
                        .padding(vertical = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "[ CREATE ACCOUNT ]",
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Black, letterSpacing = 1.sp),
                        color = if (isSignUpTab) backgroundColor else textColor
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = surfaceColor,
                shape = RoundedCornerShape(2.dp),
                border = BorderStroke(1.dp, textColor.copy(alpha = 0.15f))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    OutlinedTextField(
                        value = emailInput,
                        onValueChange = { emailInput = it },
                        label = { Text("EMAIL ADDRESS") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = textColor,
                            unfocusedBorderColor = textColor.copy(alpha = 0.3f),
                            focusedLabelColor = textColor
                        )
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = passwordInput,
                        onValueChange = { passwordInput = it },
                        label = { Text("PASSWORD") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = textColor,
                            unfocusedBorderColor = textColor.copy(alpha = 0.3f),
                            focusedLabelColor = textColor
                        )
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = {
                            if (emailInput.isNotBlank()) {
                                onLoginSuccess(emailInput, isSignUpTab)
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(2.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = textColor,
                            contentColor = backgroundColor
                        )
                    ) {
                        Text(
                            text = if (isSignUpTab) "SIGN UP & ONBOARD →" else "SIGN IN TO KAYA →",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Black,
                                letterSpacing = 1.sp
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Bypass Button
            TextButton(
                onClick = { onLoginSuccess("guest@kaya.fit", false) }
            ) {
                Text(
                    text = "— BYPASS LOGIN (GUEST ATHLETE) —",
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontFamily = FontFamily.Monospace,
                        fontSize = 10.sp,
                        letterSpacing = 1.sp
                    ),
                    color = textColor.copy(alpha = 0.6f)
                )
            }
        }
    }
}
