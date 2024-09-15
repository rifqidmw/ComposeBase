package com.aigs.base.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aigs.base.R
import com.aigs.base.ui.theme.NunitoSans
import com.aigs.base.ui.theme.PrimaryBlack
import com.aigs.base.ui.theme.PrimaryGray

@Composable
fun BaseTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isPassword: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    modifier: Modifier = Modifier
) {
    var passwordVisible by remember { mutableStateOf(false) }

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = keyboardOptions,
        singleLine = true,
        modifier = modifier
            .background(
                color = PrimaryGray,
                shape = RoundedCornerShape(60.dp)
            )
            .height(56.dp),
        textStyle = TextStyle(
            color = PrimaryBlack,
            fontSize = 14.sp,
            fontFamily = NunitoSans,
            fontWeight = FontWeight.Medium,
            lineHeight = 20.sp,
        ),
        visualTransformation = if (isPassword && !passwordVisible)
            PasswordVisualTransformation()
        else
            VisualTransformation.None,
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (leadingIcon != null) {
                    leadingIcon()
                    Spacer(Modifier.width(8.dp))
                }
                Box(Modifier.weight(1f)) {
                    if (value.isEmpty()) {
                        Text(
                            text = label,
                            color = Color.Gray,
                            fontSize = 16.sp,
                            fontFamily = NunitoSans
                        )
                    }
                    innerTextField()
                }
                if (isPassword) {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            painter = painterResource(
                                id = if (passwordVisible) R.drawable.ic_visibility
                                else R.drawable.ic_visibility_off
                            ),
                            contentDescription = if (passwordVisible) "Hide password" else "Show password",
                            tint = Color.Gray
                        )
                    }
                } else if (trailingIcon != null) {
                    trailingIcon()
                }
            }
        }
    )
}

//@Composable
//fun BaseTextField(
//    value: String,
//    onValueChange: (String) -> Unit,
//    label: String,
//    leadingIcon: @Composable (() -> Unit)? = null,
//    isPassword: Boolean = false,
//    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
//    modifier: Modifier
//) {
//    var passwordVisible by remember { mutableStateOf(false) }
//
//    OutlinedTextField(
//        value = value,
//        onValueChange = onValueChange,
//        label = { Text(label) },
//        leadingIcon = leadingIcon,
//        trailingIcon = if (isPassword) {
//            {
//                IconButton(onClick = { passwordVisible = !passwordVisible }) {
//                    Icon(
//                        painter = painterResource(
//                            id = if (passwordVisible)
//                                R.drawable.ic_visibility
//                            else
//                                R.drawable.ic_visibility_off
//                        ),
//                        contentDescription = if (passwordVisible) "Hide password" else "Show password"
//                    )
//                }
//            }
//        } else null,
//        visualTransformation = if (isPassword && !passwordVisible)
//            PasswordVisualTransformation()
//        else
//            VisualTransformation.None,
//        keyboardOptions = keyboardOptions,
//        singleLine = true,
//        modifier = modifier
//    )
//}