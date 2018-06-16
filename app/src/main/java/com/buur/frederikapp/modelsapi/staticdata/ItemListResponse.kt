package com.buur.frederikapp.modelsapi.staticdata

import com.buur.frederikapp.models.Item

class ItemListResponse(
        val type: String,
        var version: String,
        var data: Map<String, Item>
) {
}