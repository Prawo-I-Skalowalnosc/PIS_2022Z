export type MovieResponse = {
    id : string
    title: string
    rating: number
    userRating: number
    genre: string
    country_of_origin: string
    budget: number
    language: string
    releaseDate: string
    length: number
    poster_url: string
}

export type MoviesResponse = MovieResponse[]
