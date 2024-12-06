package com.gajiri.app.ui.opportunities

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gajiri.app.ui.home.HomeScreen
import com.gajiri.app.ui.opportunities.components.EmptyOpportunitiesState
import com.gajiri.app.ui.opportunities.components.FilterDialog
import com.gajiri.app.ui.opportunities.components.JobOpportunityCard
import com.gajiri.app.ui.theme.GajiriTheme

// Data model for Job Opportunity
data class JobOpportunity(
    val id: String,
    val title: String,
    val employer: String,
    val location: String,
    val description: String,
    val payEstimate: String,
    val tags: List<String>,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OpportunitiesScreen(onJobSelected: (JobOpportunity) -> Unit) {
    // Sample job opportunities
    val jobOpportunities = remember {
        listOf(
            JobOpportunity(
                id = "1",
                title = "Senior Software Engineer",
                employer = "Tech Innovations Inc.",
                location = "San Francisco, CA",
                description = "Seeking an experienced software engineer to develop cutting-edge web applications.",
                payEstimate = "$120,000 - $150,000",
                tags = listOf("Full-time", "Remote"),
            ),
            JobOpportunity(
                id = "2",
                title = "UX Designer",
                employer = "Creative Solutions LLC",
                location = "New York, NY",
                description = "Design intuitive and innovative user experiences for mobile and web platforms.",
                payEstimate = "$90,000 - $110,000",
                tags = listOf("Full-time", "Hybrid"),
            ),
            JobOpportunity(
                id = "3",
                title = "Digital Marketing Specialist",
                employer = "Growth Enterprises",
                location = "Remote",
                description = "Develop and implement comprehensive digital marketing strategies.",
                payEstimate = "$75,000 - $95,000",
                tags = listOf("Full-time", "Hybrid"),
            )
        )
    }

    // State for filter and sort
    var showFilterDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 0.dp),
                title = {
                    Text(
                        "Find Opportunities",
                        style = MaterialTheme.typography.headlineSmall
                    )
                },
                actions = {
                    IconButton(onClick = { showFilterDialog = true }) {
                        Icon(
                            imageVector = Icons.Filled.FilterList,
                            contentDescription = "Filter opportunities"
                        )
                    }
                    IconButton(onClick = { /* Sort logic */ }) {
                        Icon(
                            imageVector = Icons.Filled.Sort,
                            contentDescription = "Sort opportunities"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        // Check if there are any job opportunities
        if (jobOpportunities.isEmpty()) {
            EmptyOpportunitiesState()
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(jobOpportunities) { job ->
                    JobOpportunityCard(
                        job = job,
                        onApplyClick = {
                            onJobSelected(job)
                        }
                    )
                }
            }
        }
    }

    // Filter Dialog
    if (showFilterDialog) {
        FilterDialog(
            onDismiss = { showFilterDialog = false }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OpportunitiesScreenPreview() {
    GajiriTheme {
        OpportunitiesScreen(onJobSelected = {})
    }
}
