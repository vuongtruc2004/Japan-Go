import slugify from "slugify"

export const slugifyText = (text: string) => {
    return slugify(text, {
        lower: true
    })
}