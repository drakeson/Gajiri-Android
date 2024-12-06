package com.gajiri.app.ui.opportunities

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gajiri.app.R
import com.gajiri.app.ui.opportunities.components.CategoryHeader
import com.gajiri.app.ui.opportunities.components.FilterDialog
import com.gajiri.app.ui.opportunities.components.JobListingCard
import com.gajiri.app.ui.opportunities.components.PostJobButton
import com.gajiri.app.ui.theme.GajiriTheme

data class OpportunityCategory(
    val name: String,
    val description: String,
    val imageResId: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OpportunityCategoryDetailsScreen(serviceId: String?, onBackClick: () -> Unit) {
    // Sample Data
    val category = OpportunityCategory(
        name = "Software Development",
        description = "Explore exciting opportunities in tech, from startups to global enterprises.",
        imageResId = R.drawable.software_dev_banner
    )

    val jobs = remember {
        mutableStateListOf(
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

    // Filter and Sort States
    var selectedJobTypes by remember { mutableStateOf(setOf<String>()) }
    var selectedLocations by remember { mutableStateOf(setOf<String>()) }
    var sortOption by remember { mutableStateOf("Most Recent") }
    var isFilterDialogVisible by remember { mutableStateOf(false) }

    val filteredJobs = remember(selectedJobTypes, selectedLocations, jobs) {
        jobs.filter { job ->
            val jobTypeMatch = selectedJobTypes.isEmpty() ||
                    selectedJobTypes.any { job.tags.contains(it) }
            val locationMatch = selectedLocations.isEmpty() ||
                    selectedLocations.contains(job.location)
            jobTypeMatch && locationMatch
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("${serviceId}") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { isFilterDialogVisible = true }) {
                        Icon(Icons.Default.FilterList, contentDescription = "Filter")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Category Header
            item {
                CategoryHeader(category)
            }

            // Job Listings
            items(filteredJobs) { job ->
                JobListingCard(job)
            }

            // Post a Job CTA
            item {
                PostJobButton()
            }
        }
    }

    // Filter Dialog
    if (isFilterDialogVisible) {
        FilterDialog(
            selectedJobTypes = selectedJobTypes,
            selectedLocations = selectedLocations,
            sortOption = sortOption,
            onJobTypeToggle = { type ->
                selectedJobTypes = if (selectedJobTypes.contains(type)) {
                    selectedJobTypes - type
                } else {
                    selectedJobTypes + type
                }
            },
            onLocationToggle = { location ->
                selectedLocations = if (selectedLocations.contains(location)) {
                    selectedLocations - location
                } else {
                    selectedLocations + location
                }
            },
            onSortOptionChange = { sortOption = it },
            onDismiss = { isFilterDialogVisible = false }
        )
    }
}

// Preview
@Preview(showBackground = true)
@Composable
fun OpportunityCategoryDetailsScreenPreview() {
    GajiriTheme {
        OpportunityCategoryDetailsScreen(
            serviceId = "1", onBackClick = {}
        )
    }
}
