package com.manuelblanco.mobilechallenge.feature.events.composables

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import com.manuelblanco.mobilechallenge.core.designsystem.component.TicketsButton
import com.manuelblanco.mobilechallenge.core.designsystem.component.TicketsMultiToggleButton
import com.manuelblanco.mobilechallenge.core.designsystem.theme.TicketsTheme
import com.manuelblanco.mobilechallenge.core.model.data.Cities
import com.manuelblanco.mobilechallenge.core.model.data.EventsFilter
import com.manuelblanco.mobilechallenge.core.model.data.SortType
import com.manuelblanco.mobilechallenge.feature.events.R

/**
 * Created by Manuel Blanco Murillo on 27/2/24.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventsFilterModal(
    onDismiss: () -> Unit,
    onApply: (filters: EventsFilter) -> Unit
) {
    val modalBottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    BoxWithConstraints {
        val sheetHeight = this.constraints.maxHeight * 0.65f

        ModalBottomSheet(
            modifier = Modifier.semantics { contentDescription = "Modal Bottom Filters" },
            containerColor = Color(0xFFECEFF1),
            onDismissRequest = { onDismiss() },
            sheetState = modalBottomSheetState,
            dragHandle = { BottomSheetDefaults.DragHandle() }
        ) {
            Box(modifier = Modifier.height(with(LocalDensity.current) { sheetHeight.toDp() })) {
                FilterOptions(
                    onApply = { onApply(it) }
                )
            }
        }
    }
}

@Composable
private fun FilterOptions(
    onApply: (filters: EventsFilter) -> Unit
) {
    val context = LocalContext.current

    var sortBy by rememberSaveable { mutableStateOf(context.resources.getString(R.string.sort_name)) }
    var filterBy by rememberSaveable { mutableStateOf(Cities.ALL.city) }

    Box(
        modifier = Modifier.padding(all = TicketsTheme.dimensions.paddingMedium),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TicketsMultiToggleButton(
                modifier = Modifier.padding(
                    top = TicketsTheme.dimensions.paddingMediumDouble,
                    bottom = TicketsTheme.dimensions.paddingLarge
                ),
                title = stringResource(id = R.string.sort_events),
                selectedItem = sortBy,
                states = listOf(
                    stringResource(id = R.string.sort_name),
                    stringResource(id = R.string.sort_date),
                    stringResource(id = R.string.sort_price)
                ), onSelectedItem = { item, _ ->
                    sortBy = item
                }
            )
            TicketsMultiToggleButton(
                modifier = Modifier.padding(
                    top = TicketsTheme.dimensions.paddingMediumDouble,
                    bottom = TicketsTheme.dimensions.paddingLarge
                ),
                title = stringResource(id = R.string.filter_events),
                selectedItem = filterBy,
                states = enumValues<Cities>().map { it.city },
                onSelectedItem = { item, _ ->
                    filterBy = item
                }
            )
            Row(
                modifier = Modifier
                    .padding(
                        top = TicketsTheme.dimensions.paddingXLarge,
                        bottom = TicketsTheme.dimensions.paddingXLarge
                    )
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                TicketsButton(
                    modifier = Modifier
                        .height(TicketsTheme.dimensions.buttonDefaultHeight)
                        .width(TicketsTheme.dimensions.buttonDetailWidth),
                    onClick = {
                        sortBy = context.resources.getString(R.string.sort_name)
                        filterBy = Cities.ALL.city
                    }, label = stringResource(id = R.string.filter_reset).uppercase()
                )
                Spacer(modifier = Modifier.padding(horizontal = TicketsTheme.dimensions.paddingLarge))
                TicketsButton(
                    modifier = Modifier
                        .semantics { contentDescription = "Apply Button" }
                        .height(TicketsTheme.dimensions.buttonDefaultHeight)
                        .width(TicketsTheme.dimensions.buttonDetailWidth),
                    onClick = {
                        onApply(
                            EventsFilter(
                                sortType = getSortType(context, sortBy),
                                city = filterBy
                            )
                        )
                    }, label = stringResource(id = R.string.filter_apply).uppercase()
                )
            }
        }
    }
}

private fun getSortType(context: Context, type: String): SortType {
    return when (type) {
        context.resources.getString(R.string.sort_name) -> SortType.NONE
        context.resources.getString(R.string.sort_date) -> SortType.DATE_ASC
        context.resources.getString(R.string.sort_price) -> SortType.PRICE_ASC
        else -> SortType.NONE
    }
}

@Preview
@Composable
fun EventFilterModalPreview() {
    EventsFilterModal(onDismiss = {}, onApply = {})
}