package com.moviecatalog.todoappcompose.featureNotes.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DefaultRadioButton(
    text:String,
    checked:Boolean,
    onSelected:()->Unit,
    modifier: Modifier = Modifier
)

{

    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {

        RadioButton(
            selected = checked ,
            onClick = onSelected,
            colors = RadioButtonDefaults.colors(
                selectedColor = MaterialTheme.colorScheme.primary,
                unselectedColor = MaterialTheme.colorScheme.onBackground
            ),
        )

        Spacer(modifier = Modifier.width(2.dp))

        Text(text = text, style = MaterialTheme.typography.bodySmall)

    }
}