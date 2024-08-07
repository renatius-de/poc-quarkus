package de.renatius.poc.quarkus.library.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table
import org.hibernate.annotations.ColumnDefault
import java.util.*

@Entity
@Table(name = "student")
data class Student(
    @Id
    @Column(name = "uid", nullable = false, insertable = false, updatable = false)
    @ColumnDefault("gen_random_uuid()")
    var uid: UUID? = null,

    @Column(name = "firstname", nullable = false)
    var firstname: String? = null,

    @Column(name = "middle_name")
    var middleName: String? = null,

    @Column(name = "lastname", nullable = false)
    var lastname: String? = null,

    @Column(name = "matriculation_number", unique = true, nullable = false, updatable = false, length = 7)
    var matriculationNumber: String? = null,

    @ManyToMany(targetEntity = Course::class, cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinTable(
        name = "student_takes_part_in_course",
        joinColumns = [JoinColumn(name = "student_uid", referencedColumnName = "uid")],
        inverseJoinColumns = [JoinColumn(name = "course_uid", referencedColumnName = "uid")]
    )
    var courses: Set<Course> = emptySet(),
)
