package com.ekub.app.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ekub.app.presentation.navigation.NavigationRoute
import com.ekub.app.presentation.ui.theme.PrimaryBlue
import com.ekub.app.presentation.ui.theme.SecondaryGreen
import com.ekub.app.presentation.ui.theme.BackgroundWhite
import com.ekub.app.presentation.ui.theme.SurfaceWhite
import com.ekub.app.presentation.ui.theme.TextDark
import com.ekub.app.presentation.ui.theme.TextMedium
import com.ekub.app.presentation.ui.theme.TextLight

data class EkubGroup(
    val id: String,
    val name: String,
    val members: Int,
    val totalSavings: String,
    val nextPayout: String
)

@Composable
fun HomeScreen(navController: NavHostController) {
    var selectedTab by remember { mutableStateOf(0) }

    val sampleGroups = listOf(
        EkubGroup("1", "Addis Ekub", 12, "ETB 45,000", "Dec 15, 2024"),
        EkubGroup("2", "Business Partners", 8, "ETB 32,000", "Dec 20, 2024"),
        EkubGroup("3", "Women's Circle", 15, "ETB 60,000", "Dec 25, 2024")
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundWhite)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(PrimaryBlue)
                    .padding(24.dp)
            ) {
                Column {
                    Text(
                        text = "Welcome, Abram",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = BackgroundWhite
                    )
                    Text(
                        text = "Manage your ekub groups",
                        fontSize = 14.sp,
                        color = com.ekub.app.presentation.ui.theme.AccentLightBlue
                    )
                }
            }

            when (selectedTab) {
                0 -> HomeContent(sampleGroups, navController)
                1 -> GroupsContent(sampleGroups, navController)
                2 -> MembersContent()
                3 -> ProfileContent()
            }
        }

        // FAB
        FloatingActionButton(
            onClick = { navController.navigate(NavigationRoute.CreateGroup.route) },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(24.dp),
            containerColor = SecondaryGreen
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Create Group",
                tint = BackgroundWhite,
                modifier = Modifier.size(24.dp)
            )
        }

        // Bottom Navigation
        NavigationBar(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            containerColor = BackgroundWhite
        ) {
            NavigationBarItem(
                icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                label = { Text("Home") },
                selected = selectedTab == 0,
                onClick = { selectedTab = 0 },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = PrimaryBlue,
                    selectedTextColor = PrimaryBlue,
                    unselectedIconColor = TextLight,
                    unselectedTextColor = TextLight
                )
            )
            NavigationBarItem(
                icon = { Icon(Icons.Default.Groups, contentDescription = "Groups") },
                label = { Text("Groups") },
                selected = selectedTab == 1,
                onClick = { selectedTab = 1 },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = PrimaryBlue,
                    selectedTextColor = PrimaryBlue,
                    unselectedIconColor = TextLight,
                    unselectedTextColor = TextLight
                )
            )
            NavigationBarItem(
                icon = { Icon(Icons.Default.Groups, contentDescription = "Members") },
                label = { Text("Members") },
                selected = selectedTab == 2,
                onClick = { selectedTab = 2 },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = PrimaryBlue,
                    selectedTextColor = PrimaryBlue,
                    unselectedIconColor = TextLight,
                    unselectedTextColor = TextLight
                )
            )
            NavigationBarItem(
                icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                label = { Text("Profile") },
                selected = selectedTab == 3,
                onClick = { selectedTab = 3 },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = PrimaryBlue,
                    selectedTextColor = PrimaryBlue,
                    unselectedIconColor = TextLight,
                    unselectedTextColor = TextLight
                )
            )
        }
    }
}

@Composable
fun HomeContent(groups: List<EkubGroup>, navController: NavHostController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 80.dp)
    ) {
        item {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Your Ekub Groups",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextDark
                )
            }
        }
        items(groups) { group ->
            EkubGroupCard(group, navController)
        }
    }
}

@Composable
fun GroupsContent(groups: List<EkubGroup>, navController: NavHostController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 80.dp)
    ) {
        item {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "All Groups",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextDark
                )
            }
        }
        items(groups) { group ->
            EkubGroupCard(group, navController)
        }
    }
}

@Composable
fun MembersContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 80.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Members Section", color = TextDark)
    }
}

@Composable
fun ProfileContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 80.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Profile Section", color = TextDark)
    }
}

@Composable
fun EkubGroupCard(group: EkubGroup, navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable {
                navController.navigate(NavigationRoute.GroupDetail.createRoute(group.id))
            },
        colors = CardDefaults.cardColors(
            containerColor = SurfaceWhite
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = group.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextDark
                )
                Box(
                    modifier = Modifier
                        .background(
                            color = PrimaryBlue.copy(alpha = 0.1f),
                            shape = MaterialTheme.shapes.small
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "${group.members} Members",
                        fontSize = 12.sp,
                        color = PrimaryBlue,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Total Savings",
                        fontSize = 12.sp,
                        color = TextMedium
                    )
                    Text(
                        text = group.totalSavings,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = SecondaryGreen
                    )
                }
                Column {
                    Text(
                        text = "Next Payout",
                        fontSize = 12.sp,
                        color = TextMedium
                    )
                    Text(
                        text = group.nextPayout,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = TextDark
                    )
                }
            }
        }
    }
}
