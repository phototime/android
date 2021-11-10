package dev.zotov.phototime.domain

enum class ForecastType {
    /** There are no clouds in the sky */
    Clear,

    /** The entire sky is covered by clouds */
    Cloudy,

    /** The meteorological condition of clouds obscuring at least 95% of the sky */
    Overcast,

    /** A phenomenon caused by small droplets of water suspended in air. Synonym to fog */
    Mist,

    /** Rain that isn't heavy, or is on and off */
    PatchyRainPossible,

    /** Snow that isn't heavy, or is on and off */
    PatchySnowPossible,

    /** Sleet (rain + snow) that isn't heavy, or is on and off */
    PatchySleetPossible,

    /** Is drizzle that freezes on contact with the ground or an object at or near the surface. */
    PatchyFreezingPossible,

    /**
     * An event in which a weather system or combination of weather systems produces a multitude
     * of severe thunderstorms in a region over a continuous span of time
     */
    ThunderyOutbreaksPossible,

    /** Snow lifted from the surface by the wind */
    BlowingSnow,

    /**
     * A storm with winds of more than 56 km (35 miles) per hour for at least three hours and
     * enough snow to limit visibility to 0.4 km (0.25 mile) or less
     */
    Blizzard,

    /** Rain, when the precipitation rate is > 7.6 mm (0.30 in) per hour */
    HeavyRain,

    /** Rain, when the precipitation rate is between 2.5 mm (0.098 in) – 7.6 mm (0.30 in) per hour */
    ModerateRain,

    /** snow that falls at the same time as there is a storm with thunder and lightning */
    SnowWithThunder,
}