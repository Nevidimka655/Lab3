package com.nevidimka655.lab3

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nevidimka655.lab3.entities.Student
import java.util.*
import kotlin.concurrent.schedule

class MainVM : ViewModel() {

    private val students by lazy { fetchStudentsArray() }
    private var seconds = 0
        private set(value) {
            val h = value / 3600
            val m = (value % 3600) / 60
            val s = value % 60
            val str = "%d:%02d:%02d".format(h, m, s)
            _secondsLive.postValue(str)
            field = value
        }
    private var timer: Timer? = null
    private val _secondsLive = MutableLiveData<String>()
    val secondsLive: LiveData<String> = _secondsLive

    private fun fetchStudentsArray() = arrayOf(
        Student("Іванов Роман", "301"),
        Student("Петров Федір", "301"),
        Student("Осадча Оксана", "302"),
        Student("Максимов Руслан", "302"),
        Student("Смірнов Василь", "308"),
        Student("Потапова Марія", "309"),
        Student("Гонський Іван", "309"),
        Student("Васильєв Максим", "309")
    )

    fun searchStudentsByGroupName(groupName: String) = students.filter {
        it.groupName.contentEquals(
            other = groupName,
            ignoreCase = true
        )
    }

    fun runTimer() {
        if (timer == null) {
            timer = Timer().apply {
                schedule(0, 1000) {
                    seconds++
                }
            }
        }
    }

    fun stopTimer() {
        timer?.cancel()
        timer = null
    }

}