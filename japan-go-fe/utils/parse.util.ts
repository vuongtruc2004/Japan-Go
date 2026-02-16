export function parsePositiveInt(
    value: string | null | undefined,
): number | null {
    if (!value) return null;

    const parsed = Number(value);

    if (!Number.isInteger(parsed) || parsed <= 0) {
        return null;
    }

    return parsed;
}
