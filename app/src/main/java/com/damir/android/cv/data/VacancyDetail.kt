package com.damir.android.cv.data

import com.damir.android.cv.data.local.VacancyDetailLocal
import com.google.gson.annotations.SerializedName

data class VacancyDetail(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String?,
    @SerializedName("salary")
    val salary: Salary?,
    @SerializedName("address")
    val address: Address?,
    @SerializedName("experience")
    val experience: Experience?,
    @SerializedName("contacts")
    val contacts: Contacts?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("key_skills")
    val keySkills: List<Skill>?,
    @SerializedName("employer")
    val employer: Employer?
)

fun VacancyDetail.toLocal(): VacancyDetailLocal {
    return VacancyDetailLocal(
        id = this.id,
        name = this.name,
        from = salary?.from,
        to = salary?.to,
        currency = salary?.currency,
        city = address?.city,
        raw = address?.raw,
        experienceName = experience?.name,
        contactName = contacts?.name,
        contactsEmail = contacts?.email,
        description = this.description,
        employerName = employer?.name,
        logo240 = employer?.logoUrls?.logo240,
        logo90 = employer?.logoUrls?.logo90,
        trusted = employer?.trusted
    )
}