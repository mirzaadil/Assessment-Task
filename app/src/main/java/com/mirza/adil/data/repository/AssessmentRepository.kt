package com.mirza.adil.data.repository


import com.mirza.adil.data.remote.AssessmentService
import javax.inject.Inject

/**
 * The class AssessmentRepository
 *
 * @author  Mirza Adil
 * @email mirza.madil@gmail.com
 * @version 1.0
 * @since 14 Jul 2021
 *
 * This class is used to hold news data operations and conditions
 */
class AssessmentRepository @Inject constructor(
    private val nyService: AssessmentService,
) {
    //suspend fun getNews() = nyService.getNews()
}