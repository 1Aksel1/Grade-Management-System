export interface Jwt {

    jwt: string

}

export interface SingleMessageDto {
    message: string
}

export interface ChangePasswordDto {

    oldPassword: string,
    newPassword: string,
    newPasswordCheck: string

}

export interface UpdateEmailDto {
    email: string
}


export interface UpdateProfessorDto {

    name: string,
    surname: string,
    username: string,
    email: string,
    telephoneNumber: string,
    dateOfBirth: string,
    hireDate: string,
    subjects: string

}

export interface RegisterProfessorDto {

    name: string,
    surname: string,
    username: string,
    password: string,
    email: string,
    telephoneNumber: string,
    dateOfBirth: string,
    hireDate: string,
    subjects: string

}


export interface CourseGeneralInfoDto {

    id: number,
    name: string,
    studyProgram: string,
    ects: number

}


export interface Notification {

    id: number,
    email: string,
    subject: string,
    content: string,
    sentTimeAndDate: string

}


export interface CourseCompleteInfo {

    id: number,
    name: string,
    studyProgram: string,
    ects: number,
    studentEnrollments: Array<StudentEnrollment>,
    preExamObligations: Array<PreExamObligation>,
    preExamObligationScores: Array<PreExamObligationScore>,
    grades: Array<Grade>

}


export interface StudentEnrollment {

    id: number,
    studentIndex: string,
    status: string

}

export interface PreExamObligation {

    id: number,
    name: string,
    maxPoints: number

}

export interface PreExamObligationScore {

    id: number,
    name: string,
    studentIndex: string,
    pointsScored: number

}

export interface Grade {

    id: number,
    studentIndex: string,
    grade: number,
    examPeriod: string,
    examPoints: number

}



