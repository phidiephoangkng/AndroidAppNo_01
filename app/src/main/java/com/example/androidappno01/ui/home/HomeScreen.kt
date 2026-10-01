package com.example.androidappno01.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(vm: HomeViewModel, onOpenDetail: (Long) -> Unit) {
    val notes by vm.notes.collectAsState()
    Column(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { vm.addSample() }) { Text("Add note") }
        }
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(notes, key = { it.id }) { note ->
                Card(Modifier.fillMaxWidth().clickable { onOpenDetail(note.id) }) {
                    Column(Modifier.padding(12.dp)) {
                        Text(note.title)
                        Text(note.content, maxLines = 2)
                    }
                }
            }
        }
    }
}
