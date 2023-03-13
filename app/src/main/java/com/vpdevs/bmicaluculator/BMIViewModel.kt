package com.vpdevs.bmicaluculator

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import com.vpdevs.bmicaluculator.data.Person
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BMIViewModel : ViewModel() {

    private val _personUISate = MutableStateFlow(Person())
    val uiState = _personUISate.asStateFlow()

    fun setPerson(person: Person) {
        _personUISate.update { currentDate ->
            currentDate.copy(
                gender = person.gender,
                height = person.height,
                weight = person.weight,
                age = person.age,
                bmi = person.bmi,
                color = person.color
            )

        }
    }

}