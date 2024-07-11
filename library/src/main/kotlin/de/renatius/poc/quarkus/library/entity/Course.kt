package de.renatius.poc.quarkus.library.entity

import io.quarkus.hibernate.orm.panache.PanacheEntityBase
import org.hibernate.annotations.ColumnDefault
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.Table

@Entity
@Table(name = "course")
class Course(
    @Id
    @Column(name = "uid", nullable = false, insertable = false, updatable = false)
    @ColumnDefault("gen_random_uuid()")
    var uid: UUID = UUID.randomUUID(),

    @Column(name = "title", nullable = false, unique = true, length = 1023)
    var title: String? = null,

    @ManyToMany
    @JoinTable(
        name = "professor_holds_course",
        joinColumns = [JoinColumn(name = "course_uid", referencedColumnName = "uid")],
        inverseJoinColumns = [JoinColumn(name = "professor_uid", referencedColumnName = "uid")]
    )
    var professors: Set<Professor> = emptySet(),

    @ManyToMany
    @JoinTable(
        name = "student_takes_part_in_course",
        joinColumns = [JoinColumn(name = "course_uid", referencedColumnName = "uid")],
        inverseJoinColumns = [JoinColumn(name = "student_uid", referencedColumnName = "uid")]
    )
    var students: Set<Student> = emptySet(),
) : PanacheEntityBase()
