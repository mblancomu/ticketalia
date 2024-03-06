package com.manuelblanco.mobilechallenge.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.manuelblanco.mobilechallenge.core.ui.R

/**
 * Created by Manuel Blanco Murillo on 31/1/24.
 */

@Composable
fun NetworkError(
    modifier: Modifier = Modifier,
    onRetryButtonClick: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(R.string.network_error_title),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
        )

        Text(
            text = stringResource(R.string.network_error_description),
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium)),
            textAlign = TextAlign.Center,
        )

        Button(onClick = { onRetryButtonClick() }) {
            Text(
                text = stringResource(R.string.network_error_retry_button_text).uppercase()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NetworkErrorScreenPreview() {
    NetworkError(modifier = Modifier, onRetryButtonClick = {})
}