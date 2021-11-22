package com.assignment.payhere.user.domain.embeded

import lombok.EqualsAndHashCode
import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.validation.constraints.Size

@Embeddable
@EqualsAndHashCode
class Password(
    @Size(min = 6, max = 20)
    @Column
    val value: String = "",
): Serializable