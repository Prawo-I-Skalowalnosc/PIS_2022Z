export type ErrorResponse = {
    status : number
    message : string // tego nie powinien widzieć user
    infoMessage : string // to pokazywać userowi
    timestamp : Date
}