package com.buur.frederikapp.modelsapi

import com.buur.frederikapp.models.Item

class ItemListResponse(
        val type: String,
        var version: String,
        var data: Map<String, Item>
) {
}