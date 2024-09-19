package com.aigs.base.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aigs.base.common.AppConstants.Language
import com.aigs.base.ui.theme.dangerRed
import com.aigs.base.ui.theme.nunitoSans
import com.aigs.base.ui.theme.primaryBlack
import com.aigs.base.ui.theme.primaryBlue

@Composable
fun LanguageSelectionItem(
    language: Language,
    isSelected: Boolean,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier
) {
    val bgColor by animateColorAsState(
        if (isSelected) Color(0xFFE5EBFC)
        else Color(0xFFF9F9F9)
    )

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clip(RoundedCornerShape(12.dp)),
        color = bgColor,
        tonalElevation = if (isSelected) 8.dp else 0.dp,
        shadowElevation = if (isSelected) 4.dp else 0.dp
    ) {
        Row(
            modifier = Modifier
                .clickable(onClick = onSelect)
                .padding(vertical = 16.dp, horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = language.name,
                    fontFamily = nunitoSans,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = primaryBlack,
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            RadioButton(
                selected = isSelected,
                onClick = null,
                colors = RadioButtonDefaults.colors(
                    selectedColor = primaryBlue,
                    unselectedColor = dangerRed
                )
            )
        }
    }

}