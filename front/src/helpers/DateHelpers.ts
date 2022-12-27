export function SimplifyDate (date: string) : string {
    return date?.split('T')[0]; //YYYY-MM-DD
}