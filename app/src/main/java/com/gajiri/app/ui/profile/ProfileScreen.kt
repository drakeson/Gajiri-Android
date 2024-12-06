package com.gajiri.app.ui.profile

import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.gajiri.app.R
import com.gajiri.app.ui.profile.components.ActivitySummaryItem
import com.gajiri.app.ui.profile.components.SectionHeader
import com.gajiri.app.ui.profile.components.SettingsSwitch
import com.gajiri.app.ui.profile.components.SkillProgressItem
import com.gajiri.app.ui.theme.GajiriTheme

data class SkillItem(val name: String, val progress: Float)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onLogout: () -> Unit
) {
    var userName by remember { mutableStateOf("John Doe") }
    var email by remember { mutableStateOf("john.doe@example.com") }
    var skills by remember {
        mutableStateOf(listOf(
            SkillItem("Android Development", 0.8f),
            SkillItem("Kotlin", 0.9f),
            SkillItem("UI/UX Design", 0.6f)
        ))
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "My Profile",
                        style = MaterialTheme.typography.headlineSmall
                    )
                },
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // Profile Header
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_greeting_image),
                            contentDescription = "Profile Picture",
                            modifier = Modifier
                                .size(80.dp)
                                .clip(MaterialTheme.shapes.large),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text(
                                text = userName,
                                style = MaterialTheme.typography.headlineSmall
                            )
                            Text(
                                text = email,
                                style = MaterialTheme.typography.labelSmall
                            )
                            IconButton(
                                onClick = { /* Edit Profile */ },
                                modifier = Modifier.size(20.dp)
                            ) {
                                Icon(Icons.Default.Edit, contentDescription = "Edit Profile")
                            }
                        }
                    }
                }
            }

            // Skills Section
            item {
                SectionHeader("Skills & Certifications")
                skills.forEach { skill ->
                    SkillProgressItem(skill)
                }
            }

            // Activity Summary
            item {
                SectionHeader("Activity Summary")
                ActivitySummaryItem(
                    unreadMessages = 15,
                    jobsCompleted = 42,
                    averageRating = 4.7f,
                    totalEarnings = "$5,600"
                )
            }

            // Account Settings
            item {
                SectionHeader("Account Settings")
                SettingsSwitch("Notifications", remember { mutableStateOf(true) })
                SettingsSwitch("Dark Mode", remember { mutableStateOf(false) })
            }

            // Logout Button
            item {
                Button(
                    onClick = onLogout,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text("Logout", color = Color.White)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    GajiriTheme {
        ProfileScreen(onLogout = { /* Handle Logout */ })
    }
}
