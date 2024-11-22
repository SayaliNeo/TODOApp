package com.moviecatalog.todoappcompose.featureNotes.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.moviecatalog.todoappcompose.featureNotes.domain.utils.NotesOrderType
import com.moviecatalog.todoappcompose.featureNotes.domain.utils.OrderType
import com.moviecatalog.todoappcompose.featureNotes.presentation.util.TestTags


@Composable
fun OrderSectionScreen(
    modifier: Modifier = Modifier,
    noteOrder: NotesOrderType = NotesOrderType.Time(OrderType.Descending),
    onOrderChange: (NotesOrderType) -> Unit
) {

    Column(modifier = modifier) {
        Row(modifier = modifier.fillMaxWidth()) {

            DefaultRadioButton(
                modifier = Modifier.testTag(TestTags.ORDER_SECTION),
                text = "Title",
                checked = noteOrder is NotesOrderType.Title,
                onSelected = {
                    onOrderChange(NotesOrderType.Title(noteOrder.orderType))
                })

            Spacer(modifier = Modifier.width(3.dp))

            DefaultRadioButton(
                text = "Color",
                checked = noteOrder is NotesOrderType.Color,
                onSelected = {
                    onOrderChange(NotesOrderType.Color(noteOrder.orderType))
                })

            Spacer(modifier = Modifier.width(3.dp))

            DefaultRadioButton(
                modifier = Modifier.testTag(TestTags.ORDER_DATE),
                text = "Date",
                checked = noteOrder is NotesOrderType.Time,
                onSelected = {
                    onOrderChange(NotesOrderType.Time(noteOrder.orderType))
                })

        }

        Spacer(modifier = Modifier.height(4.dp))

        Row (modifier = Modifier.fillMaxWidth()){
            DefaultRadioButton(
                text = "Ascending",
                checked = noteOrder.orderType is OrderType.Ascending,
                onSelected = {
                    onOrderChange(noteOrder.copy(OrderType.Ascending))
                })

            Spacer(modifier = Modifier.width(3.dp))

            DefaultRadioButton(
                text = "Descending",
                checked = noteOrder.orderType  is OrderType.Descending,
                onSelected = {
                    onOrderChange(noteOrder.copy(OrderType.Descending))
                })


        }
    }
}