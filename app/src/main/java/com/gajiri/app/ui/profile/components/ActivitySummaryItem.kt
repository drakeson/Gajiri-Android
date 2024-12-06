package com.gajiri.app.ui.profile.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ActivitySummaryItem(
    unreadMessages: Int,
    jobsCompleted: Int,
    averageRating: Float,
    totalEarnings: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text("Messages: $unreadMessages Unread")
        Text("Jobs Completed: $jobsCompleted")
        Text("Average Rating: $averageRating")
        Text("Total Earnings: $totalEarnings")
    }
}