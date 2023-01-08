export type UserRate = {
    movieId : string
    rating : number
}

export type UserRateResponse = UserRate & {
    isNew : boolean
}