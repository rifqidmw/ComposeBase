package com.aigs.base.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.aigs.base.ui.theme.PrimaryBlue
import com.aigs.base.ui.theme.Typography

enum class ButtonStyle {
    PRIMARY,
    SECONDARY,
    TEXT,
    ICON_ONLY
}

@Composable
fun BaseButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    style: ButtonStyle = ButtonStyle.PRIMARY,
    text: String? = null,
    textStyle: TextStyle = Typography.bodyLarge,
    icon: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    enabled: Boolean = true,
    profileImage: Painter? = null,
    shape: Shape = RoundedCornerShape(16.dp),
    contentPadding: PaddingValues = PaddingValues(vertical = 16.dp),
    containerColor: Color = Color.Unspecified,
    contentColor: Color = Color.Unspecified
) {
    val buttonColors = ButtonDefaults.buttonColors(
        containerColor = when (style) {
            ButtonStyle.PRIMARY, ButtonStyle.ICON_ONLY -> containerColor.takeIf { it != Color.Unspecified }
                ?: PrimaryBlue

            ButtonStyle.SECONDARY, ButtonStyle.TEXT -> Color.Transparent
        },
        contentColor = when (style) {
            ButtonStyle.PRIMARY, ButtonStyle.ICON_ONLY -> contentColor.takeIf { it != Color.Unspecified }
                ?: Color.White

            ButtonStyle.SECONDARY, ButtonStyle.TEXT -> contentColor.takeIf { it != Color.Unspecified }
                ?: PrimaryBlue
        }
    )

    val buttonShape = if (style == ButtonStyle.ICON_ONLY) CircleShape else shape

    val buttonModifier = if (style == ButtonStyle.TEXT) {
        modifier.then(Modifier.padding(contentPadding))
    } else {
        modifier
    }

    val content: @Composable RowScope.() -> Unit = {
        when (style) {
            ButtonStyle.ICON_ONLY -> icon?.invoke()
            else -> {
                leadingIcon?.let {
                    it()
                    Spacer(Modifier.width(16.dp))
                }
                profileImage?.let {
                    Image(
                        painter = it,
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
                            .clip(
                                CircleShape
                            ),
                    )
                    Spacer(Modifier.width(8.dp))
                }
                text?.let { Text(it, style = textStyle) }
                trailingIcon?.let {
                    Spacer(Modifier.width(16.dp))
                    it()
                }
            }
        }
    }

    when (style) {
        ButtonStyle.PRIMARY, ButtonStyle.ICON_ONLY -> {
            Button(
                onClick = onClick,
                modifier = buttonModifier,
                colors = buttonColors,
                shape = buttonShape,
                enabled = enabled,
                content = content,
                contentPadding = contentPadding
            )
        }

        ButtonStyle.SECONDARY -> {
            OutlinedButton(
                onClick = onClick,
                modifier = buttonModifier,
                colors = buttonColors,
                shape = buttonShape,
                enabled = enabled,
                content = content,
                contentPadding = contentPadding
            )
        }

        ButtonStyle.TEXT -> {
            Box(
                modifier = buttonModifier
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        enabled = enabled,
                        onClick = onClick,
                    ),
                contentAlignment = Alignment.Center
            ) {
                ProvideTextStyle(value = textStyle) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        content = content
                    )
                }
            }
        }
    }
}