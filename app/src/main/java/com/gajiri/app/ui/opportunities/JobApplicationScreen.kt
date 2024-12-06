package com.gajiri.app.ui.opportunities

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gajiri.app.ui.opportunities.components.JobTag
import com.gajiri.app.ui.theme.GajiriTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobApplicationScreen(jobId: String?, onBackClick: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var resumeFileName by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }
    var submissionStatus by remember { mutableStateOf<String?>(null) }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Job Application") },
                navigationIcon = {
                    IconButton(onClick = { onBackClick() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                // Job Details Section
                Text(
                    text = "Senior Software Engineer",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("TechCorp Ltd.", style = MaterialTheme.typography.titleMedium)
                    Text("Nairobi, Kenya", style = MaterialTheme.typography.titleMedium)
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Job Tags
                Row {
                    JobTag("Full-time")
                    JobTag("Remote")
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Pay Estimate
                Text(
                    text = "UGX 2,000,000 - UGX 3,500,000",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Job Description
                Text(
                    text = "Job Description",
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = "We are seeking a talented Senior Software Engineer to join our innovative team. The ideal candidate will have extensive experience in mobile and backend development...",
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Application Form
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Full Name") },
                    modifier = Modifier.fillMaxWidth(),
                    isError = name.isEmpty(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )

                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email Address") },
                    modifier = Modifier.fillMaxWidth(),
                    isError = email.isEmpty() || !email.contains("@"),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )

                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = { Text("Phone Number") },
                    modifier = Modifier.fillMaxWidth(),
                    isError = phone.isEmpty(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                )

                Spacer(modifier = Modifier.height(8.dp))

                // File Upload
                Button(
                    onClick = { /* Implement file picker */ },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(Icons.Default.AttachFile, contentDescription = "Attach Resume")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(resumeFileName ?: "Upload Resume")
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Submission Status
                submissionStatus?.let { status ->
                    Text(
                        text = status,
                        color = if (status.contains("Success")) Color.Green else Color.Red,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Submit Button
                Button(
                    onClick = {
                        // Validate fields
                        if (name.isNotEmpty() && email.contains("@") && phone.isNotEmpty()) {
                            isLoading = true
                            scope.launch {
                                // Simulate application submission
                                try {
                                    // Actual submission logic would go here
                                    Thread.sleep(2000) // Simulated network delay
                                    submissionStatus = "Application Submitted Successfully!"
                                } catch (e: Exception) {
                                    submissionStatus = "Submission Failed. Please try again."
                                } finally {
                                    isLoading = false
                                }
                            }
                        } else {
                            submissionStatus = "Please fill all required fields correctly"
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !isLoading
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(
                            color = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    } else {
                        Text("Submit Application")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun JobApplicationScreenPreview() {
    GajiriTheme {
        JobApplicationScreen(jobId = "1", onBackClick = {})
    }
}