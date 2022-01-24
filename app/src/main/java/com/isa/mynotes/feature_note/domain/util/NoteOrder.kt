package com.isa.mynotes.feature_note.domain.util

sealed class NoteOrder(val orderType: OrderType) {

    class Title(oderType: OrderType): NoteOrder(oderType)
    class Date(orderType: OrderType): NoteOrder(orderType)
    class Color(orderType: OrderType): NoteOrder(orderType)
}