package com.miladsh.mytodoList.utils

import java.util.*

class Gregorian {

    inner class SolarCalendar {
        var strWeekday = ""
        var strMonth = ""
        var date = 0
        var month = 0
        var year = 0

        init {
            val gregorian = Date()
            calcSolarCalendar(gregorian)
        }

        private fun calcSolarCalendar(gregorian: Date) {
            val ld: Int
            val gregorianYear: Int = gregorian.year + 1900
            val gregorianMonth: Int = gregorian.month + 1
            val gregorianDate: Int = gregorian.date
            val weekDay: Int = gregorian.day

            val gregorian = IntArray(12)
            val solar = IntArray(12)
            gregorian[0] = 0
            gregorian[1] = 31
            gregorian[2] = 59
            gregorian[3] = 90
            gregorian[4] = 120
            gregorian[5] = 151
            gregorian[6] = 181
            gregorian[7] = 212
            gregorian[8] = 243
            gregorian[9] = 273
            gregorian[10] = 304
            gregorian[11] = 334

            solar[0] = 0
            solar[1] = 31
            solar[2] = 60
            solar[3] = 91
            solar[4] = 121
            solar[5] = 152
            solar[6] = 182
            solar[7] = 213
            solar[8] = 244
            solar[9] = 274
            solar[10] = 305
            solar[11] = 335

            if (gregorianYear % 4 != 0) {
                date = gregorian[gregorianMonth - 1] + gregorianDate
                if (date > 79) {
                    date -= 79
                    if (date <= 186) {
                        when (date % 31) {
                            0 -> {
                                month = date / 31
                                date = 31
                            }
                            else -> {
                                month = date / 31 + 1
                                date %= 31
                            }
                        }
                        year = gregorianYear - 621
                    } else {
                        date -= 186
                        when (date % 30) {
                            0 -> {
                                month = date / 30 + 6
                                date = 30
                            }
                            else -> {
                                month = date / 30 + 7
                                date %= 30
                            }
                        }
                        year = gregorianYear - 621
                    }
                } else {
                    ld = if (gregorianYear > 1996 && gregorianYear % 4 == 1) {
                        11
                    } else {
                        10
                    }
                    date += ld
                    when (date % 30) {
                        0 -> {
                            month = date / 30 + 9
                            date = 30
                        }
                        else -> {
                            month = date / 30 + 10
                            date %= 30
                        }
                    }
                    year = gregorianYear - 622
                }
            } else {
                date = solar[gregorianMonth - 1] + gregorianDate
                ld = if (gregorianYear >= 1996) {
                    79
                } else {
                    80
                }
                if (date > ld) {
                    date -= ld
                    if (date <= 186) {
                        when (date % 31) {
                            0 -> {
                                month = date / 31
                                date = 31
                            }
                            else -> {
                                month = date / 31 + 1
                                date %= 31
                            }
                        }
                        year = gregorianYear - 621
                    } else {
                        date -= 186
                        when (date % 30) {
                            0 -> {
                                month = date / 30 + 6
                                date = 30
                            }
                            else -> {
                                month = date / 30 + 7
                                date %= 30
                            }
                        }
                        year = gregorianYear - 621
                    }
                } else {
                    date += 10
                    when (date % 30) {
                        0 -> {
                            month = date / 30 + 9
                            date = 30
                        }
                        else -> {
                            month = date / 30 + 10
                            date %= 30
                        }
                    }
                    year = gregorianYear - 622
                }
            }

            when (month) {
                1 -> strMonth = "??????????????"
                2 -> strMonth = "????????????????"
                3 -> strMonth = "??????????"
                4 -> strMonth = "??????"
                5 -> strMonth = "??????????"
                6 -> strMonth = "????????????"
                7 -> strMonth = "??????"
                8 -> strMonth = "????????"
                9 -> strMonth = "??????"
                10 -> strMonth = "????"
                11 -> strMonth = "????????"
                12 -> strMonth = "??????????"
            }
            when (weekDay) {
                0 -> strWeekday = "????????????"
                1 -> strWeekday = "????????????"
                2 -> strWeekday = "???? ????????"
                3 -> strWeekday = "????????????????"
                4 -> strWeekday = "?????? ????????"
                5 -> strWeekday = "????????"
                6 -> strWeekday = "????????"
            }
        }
    }

    fun getCurrentSolarDate(): String {
        val gregorian = Gregorian()
        val sc: SolarCalendar = gregorian.SolarCalendar()

        return "${sc.date.withPersianDigits} ${sc.strMonth} ${sc.year.withPersianDigits}"
    }
}