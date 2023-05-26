package com.expeknow.store

data class SampleData (
    val appName: String, val appLogo: Int, val size: Int,
    val graphic: Int, val stars: Float
        )

fun Apps() : List<SampleData>{
    val appList = arrayListOf<SampleData>()
    appList.add(SampleData("Life Calender",
        R.drawable.logo, 20, R.drawable.gra, 4.5f))
    appList.add(SampleData("Quiz App",
        R.drawable.loho, 20, R.drawable.ap, 3.6f))
    appList.add(SampleData("Task Wally",
        R.drawable.applogo, 20, R.drawable.lc, 4.6f))
    appList.add(SampleData("Fit track",
        R.drawable.icon, 20, R.drawable.gra, 4.9f))

    return appList

}