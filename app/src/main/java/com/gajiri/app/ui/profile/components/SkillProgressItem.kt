package com.gajiri.app.ui.profile.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gajiri.app.ui.profile.SkillItem

@Composable
fun SkillProgressItem(skill: SkillItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = skill.name,
            modifier = Modifier.weight(1f)
        )
        LinearProgressIndicator(
            progress = skill.progress,
            modifier = Modifier
                .width(100.dp)
                .height(8.dp)
        )
    }
}

