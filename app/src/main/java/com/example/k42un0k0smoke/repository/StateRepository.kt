package com.example.k42un0k0smoke.repository

import com.example.k42un0k0smoke.model.State

interface StateRepository {
    fun save(state: State);
    fun find(): State;
}