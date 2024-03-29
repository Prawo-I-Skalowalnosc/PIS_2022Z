export type Credentials = {
    username: string
    password: string
}

export type RegisterCredentials = Credentials & {
    confirmPassword: string
    email: string
}

export type LoginResponse = {
    success : boolean
    token : string
    message : string
    username : string
    roles : []
}

export type RegisterResponse = {
    success : boolean
    message : string
}