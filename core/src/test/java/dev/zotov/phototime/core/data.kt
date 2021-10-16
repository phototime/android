package dev.zotov.phototime.core

import dev.zotov.phototime.shared.models.Forecast
import dev.zotov.phototime.shared.models.HourlyForecast
import java.time.LocalDateTime

val successForecastApiResponse =
    "{\"location\":{\"name\":\"Perm\",\"region\":\"Perm'\",\"country\":\"Russia\",\"lat\":58.0,\"lon\":56.25,\"tz_id\":\"Asia/Yekaterinburg\",\"localtime_epoch\":1634402321,\"localtime\":\"2021-10-16 21:38\"},\"current\":{\"last_updated_epoch\":1634401800,\"last_updated\":\"2021-10-16 21:30\",\"temp_c\":5.0,\"temp_f\":41.0,\"is_day\":0,\"condition\":{\"text\":\"Clear\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/night/113.png\",\"code\":1000},\"wind_mph\":11.9,\"wind_kph\":19.1,\"wind_degree\":200,\"wind_dir\":\"SSW\",\"pressure_mb\":1018.0,\"pressure_in\":30.06,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":65,\"cloud\":0,\"feelslike_c\":2.5,\"feelslike_f\":36.5,\"vis_km\":10.0,\"vis_miles\":6.0,\"uv\":1.0,\"gust_mph\":10.7,\"gust_kph\":17.3},\"forecast\":{\"forecastday\":[{\"date\":\"2021-10-16\",\"date_epoch\":1634342400,\"day\":{\"maxtemp_c\":7.6,\"maxtemp_f\":45.7,\"mintemp_c\":5.4,\"mintemp_f\":41.7,\"avgtemp_c\":6.7,\"avgtemp_f\":44.1,\"maxwind_mph\":11.2,\"maxwind_kph\":18.0,\"totalprecip_mm\":0.7,\"totalprecip_in\":0.03,\"avgvis_km\":10.0,\"avgvis_miles\":6.0,\"avghumidity\":73.0,\"daily_will_it_rain\":1,\"daily_chance_of_rain\":85,\"daily_will_it_snow\":0,\"daily_chance_of_snow\":0,\"condition\":{\"text\":\"Patchy rain possible\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/day/176.png\",\"code\":1063},\"uv\":1.0},\"astro\":{\"sunrise\":\"07:53 AM\",\"sunset\":\"06:08 PM\",\"moonrise\":\"05:49 PM\",\"moonset\":\"01:39 AM\",\"moon_phase\":\"Full Moon\",\"moon_illumination\":\"76\"},\"hour\":[{\"time_epoch\":1634324400,\"time\":\"2021-10-16 00:00\",\"temp_c\":8.6,\"temp_f\":47.5,\"is_day\":0,\"condition\":{\"text\":\"Clear\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/night/113.png\",\"code\":1000},\"wind_mph\":10.3,\"wind_kph\":16.6,\"wind_degree\":225,\"wind_dir\":\"SW\",\"pressure_mb\":1016.0,\"pressure_in\":30.0,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":71,\"cloud\":23,\"feelslike_c\":6.0,\"feelslike_f\":42.8,\"windchill_c\":6.0,\"windchill_f\":42.8,\"heatindex_c\":8.6,\"heatindex_f\":47.5,\"dewpoint_c\":3.7,\"dewpoint_f\":38.7,\"will_it_rain\":0,\"chance_of_rain\":0,\"will_it_snow\":0,\"chance_of_snow\":0,\"vis_km\":10.0,\"vis_miles\":6.0,\"gust_mph\":15.9,\"gust_kph\":25.6,\"uv\":1.0},{\"time_epoch\":1634328000,\"time\":\"2021-10-16 01:00\",\"temp_c\":7.3,\"temp_f\":45.1,\"is_day\":0,\"condition\":{\"text\":\"Patchy rain possible\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/night/176.png\",\"code\":1063},\"wind_mph\":9.8,\"wind_kph\":15.8,\"wind_degree\":216,\"wind_dir\":\"SW\",\"pressure_mb\":1016.0,\"pressure_in\":30.0,\"precip_mm\":0.1,\"precip_in\":0.0,\"humidity\":73,\"cloud\":84,\"feelslike_c\":4.5,\"feelslike_f\":40.1,\"windchill_c\":4.5,\"windchill_f\":40.1,\"heatindex_c\":7.3,\"heatindex_f\":45.1,\"dewpoint_c\":2.9,\"dewpoint_f\":37.2,\"will_it_rain\":0,\"chance_of_rain\":62,\"will_it_snow\":0,\"chance_of_snow\":0,\"vis_km\":10.0,\"vis_miles\":6.0,\"gust_mph\":16.1,\"gust_kph\":25.9,\"uv\":1.0},{\"time_epoch\":1634331600,\"time\":\"2021-10-16 02:00\",\"temp_c\":6.6,\"temp_f\":43.9,\"is_day\":0,\"condition\":{\"text\":\"Partly cloudy\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/night/116.png\",\"code\":1003},\"wind_mph\":10.3,\"wind_kph\":16.6,\"wind_degree\":213,\"wind_dir\":\"SSW\",\"pressure_mb\":1016.0,\"pressure_in\":30.0,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":74,\"cloud\":54,\"feelslike_c\":3.5,\"feelslike_f\":38.3,\"windchill_c\":3.5,\"windchill_f\":38.3,\"heatindex_c\":6.6,\"heatindex_f\":43.9,\"dewpoint_c\":2.3,\"dewpoint_f\":36.1,\"will_it_rain\":0,\"chance_of_rain\":0,\"will_it_snow\":0,\"chance_of_snow\":0,\"vis_km\":10.0,\"vis_miles\":6.0,\"gust_mph\":16.8,\"gust_kph\":27.0,\"uv\":1.0},{\"time_epoch\":1634335200,\"time\":\"2021-10-16 03:00\",\"temp_c\":6.5,\"temp_f\":43.7,\"is_day\":0,\"condition\":{\"text\":\"Overcast\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/night/122.png\",\"code\":1009},\"wind_mph\":10.3,\"wind_kph\":16.6,\"wind_degree\":209,\"wind_dir\":\"SSW\",\"pressure_mb\":1016.0,\"pressure_in\":30.0,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":72,\"cloud\":95,\"feelslike_c\":3.4,\"feelslike_f\":38.1,\"windchill_c\":3.4,\"windchill_f\":38.1,\"heatindex_c\":6.5,\"heatindex_f\":43.7,\"dewpoint_c\":1.8,\"dewpoint_f\":35.2,\"will_it_rain\":0,\"chance_of_rain\":0,\"will_it_snow\":0,\"chance_of_snow\":0,\"vis_km\":10.0,\"vis_miles\":6.0,\"gust_mph\":16.6,\"gust_kph\":26.6,\"uv\":1.0},{\"time_epoch\":1634338800,\"time\":\"2021-10-16 04:00\",\"temp_c\": 7.2,\"temp_f\":45.0,\"is_day\":0,\"condition\":{\"text\":\"Patchy rain possible\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/night/176.png\",\"code\":1063},\"wind_mph\":10.7,\"wind_kph\":17.3,\"wind_degree\":207,\"wind_dir\":\"SSW\",\"pressure_mb\":1015.0,\"pressure_in\":29.98,\"precip_mm\":0.1,\"precip_in\":0.0,\"humidity\":63,\"cloud\":81,\"feelslike_c\":4.2,\"feelslike_f\":39.6,\"windchill_c\":4.2,\"windchill_f\":39.6,\"heatindex_c\":7.2,\"heatindex_f\":45.0,\"dewpoint_c\":0.7,\"dewpoint_f\":33.3,\"will_it_rain\":0,\"chance_of_rain\":64,\"will_it_snow\":0,\"chance_of_snow\":0,\"vis_km\":10.0,\"vis_miles\":6.0,\"gust_mph\":16.3,\"gust_kph\":26.3,\"uv\":1.0},{\"time_epoch\":1634342400,\"time\":\"2021-10-16 05:00\",\"temp_c\":7.1,\"temp_f\":44.8,\"is_day\":0,\"condition\":{\"text\":\"Cloudy\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/night/119.png\",\"code\":1006},\"wind_mph\":11.2,\"wind_kph\":18.0,\"wind_degree\":217,\"wind_dir\":\"SW\",\"pressure_mb\":1016.0,\"pressure_in\":29.99,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":61,\"cloud\":78,\"feelslike_c\":4.0,\"feelslike_f\":39.2,\"windchill_c\":4.0,\"windchill_f\":39.2,\"heatindex_c\":7.1,\"heatindex_f\":44.8,\"dewpoint_c\":0.1,\"dewpoint_f\":32.2,\"will_it_rain\":0,\"chance_of_rain\":0,\"will_it_snow\":0,\"chance_of_snow\":0,\"vis_km\":10.0,\"vis_miles\":6.0,\"gust_mph\":17.2,\"gust_kph\":27.7,\"uv\":1.0},{\"time_epoch\":1634346000,\"time\":\"2021-10-16 06:00\",\"temp_c\":6.8,\"temp_f\":44.2,\"is_day\":0,\"condition\":{\"text\":\"Overcast\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/night/122.png\",\"code\":1009},\"wind_mph\":11.0,\"wind_kph\":17.6,\"wind_degree\":227,\"wind_dir\":\"SW\",\"pressure_mb\":1016.0,\"pressure_in\":29.99,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":63,\"cloud\":100,\"feelslike_c\":3.6,\"feelslike_f\":38.5,\"windchill_c\":3.6,\"windchill_f\":38.5,\"heatindex_c\":6.8,\"heatindex_f\":44.2,\"dewpoint_c\":0.2,\"dewpoint_f\":32.4,\"will_it_rain\":0,\"chance_of_rain\":0,\"will_it_snow\":0,\"chance_of_snow\":0,\"vis_km\":10.0,\"vis_miles\":6.0,\"gust_mph\":16.6,\"gust_kph\":26.6,\"uv\":1.0},{\"time_epoch\":1634349600,\"time\":\"2021-10-16 07:00\",\"temp_c\":6.4,\"temp_f\":43.5,\"is_day\":0,\"condition\":{\"text\":\"Overcast\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/night/122.png\",\"code\":1009},\"wind_mph\":10.7,\"wind_kph\":17.3,\"wind_degree\":224,\"wind_dir\":\"SW\",\"pressure_mb\":1016.0,\"pressure_in\":30.0,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":68,\"cloud\":100,\"feelslike_c\":3.2,\"feelslike_f\":37.8,\"windchill_c\":3.2,\"windchill_f\":37.8,\"heatindex_c\":6.4,\"heatindex_f\":43.5,\"dewpoint_c\":1.0,\"dewpoint_f\":33.8,\"will_it_rain\":0,\"chance_of_rain\":0,\"will_it_snow\":0,\"chance_of_snow\":0,\"vis_km\":10.0,\"vis_miles\":6.0,\"gust_mph\":15.7,\"gust_kph\":25.2,\"uv\":1.0},{\"time_epoch\":1634353200,\"time\":\"2021-10-16 08:00\",\"temp_c\":6.2,\"temp_f\":43.2,\"is_day\":1,\"condition\":{\"text\":\"Overcast\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/day/122.png\",\"code\":1009},\"wind_mph\":11.0,\"wind_kph\":17.6,\"wind_degree\":223,\"wind_dir\":\"SW\",\"pressure_mb\":1016.0,\"pressure_in\":30.0,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":71,\"cloud\":100,\"feelslike_c\":2.9,\"feelslike_f\":37.2,\"windchill_c\":2.9,\"windchill_f\":37.2,\"heatindex_c\":6.2,\"heatindex_f\":43.2,\"dewpoint_c\":1.4,\"dewpoint_f\":34.5,\"will_it_rain\":0,\"chance_of_rain\":0,\"will_it_snow\":0,\"chance_of_snow\":0,\"vis_km\":10.0,\"vis_miles\":6.0,\"gust_mph\":15.7,\"gust_kph\":25.2,\"uv\":2.0},{\"time_epoch\":1634356800,\"time\":\"2021-10-16 09:00\",\"temp_c\":5.8,\"temp_f\":42.4,\"is_day\":1,\"condition\":{\"text\":\"Overcast\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/day/122.png\",\"code\":1009},\"wind_mph\":11.2,\"wind_kph\":18.0,\"wind_degree\":225,\"wind_dir\":\"SW\",\"pressure_mb\":1016.0,\"pressure_in\":30.01,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":81,\"cloud\":100,\"feelslike_c\":2.3,\"feelslike_f\":36.1,\"windchill_c\":2.3,\"windchill_f\":36.1,\"heatindex_c\":5.8,\"heatindex_f\":42.4,\"dewpoint_c\":2.8,\"dewpoint_f\":37.0,\"will_it_rain\":0,\"chance_of_rain\":0,\"will_it_snow\":0,\"chance_of_snow\":0,\"vis_km\":10.0,\"vis_miles\":6.0,\"gust_mph\":15.2,\"gust_kph\":24.5,\"uv\":2.0},{\"time_epoch\":1634360400,\"time\":\"2021-10-16 10:00\",\"temp_c\":5.4,\"temp_f\":41.7,\"is_day\":1,\"condition\":{\"text\":\"Patchy rain possible\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/day/1 76.png\",\"code\":1063},\"wind_mph\":10.3,\"wind_kph\":16.6,\"wind_degree\":225,\"wind_dir\":\"SW\",\"pressure_mb\":1017.0,\"pressure_in\":30.02,\"precip_mm\":0.1,\"precip_in\":0.0,\"humidity\":92,\"cloud\":100,\"feelslike_c\":2.0,\"feelslike_f\":35.6,\"windchill_c\":2.0,\"windchill_f\":35.6,\"heatindex_c\":5.4,\"heatindex_f\":41.7,\"dewpoint_c\":4.2,\"dewpoint_f\":39.6,\"will_it_rain\":1,\"chance_of_rain\":78,\"will_it_snow\":0,\"chance_of_snow\":0,\"vis_km\":10.0,\"vis_miles\":6.0,\"gust_mph\":14.3,\"gust_kph\":23.0,\"uv\":2.0},{\"time_epoch\":1634364000,\"time\":\"2021-10-16 11:00\",\"temp_c\":6.0,\"temp_f\":42.8,\"is_day\":1,\"condition\":{\"text\":\"Patchy rain possible\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/day/176.png\",\"code\":1063},\"wind_mph\":10.5,\"wind_kph\":16.9,\"wind_degree\":233,\"wind_dir\":\"SW\",\"pressure_mb\":1017.0,\"pressure_in\":30.03,\"precip_mm\":0.1,\"precip_in\":0.0,\"humidity\":90,\"cloud\":100,\"feelslike_c\":2.7,\"feelslike_f\":36.9,\"windchill_c\":2.7,\"windchill_f\":36.9,\"heatindex_c\":6.0,\"heatindex_f\":42.8,\"dewpoint_c\":4.5,\"dewpoint_f\":40.1,\"will_it_rain\":1,\"chance_of_rain\":75,\"will_it_snow\":0,\"chance_of_snow\":0,\"vis_km\":10.0,\"vis_miles\":6.0,\"gust_mph\":13.9,\"gust_kph\":22.3,\"uv\":2.0},{\"time_epoch\":1634367600,\"time\":\"2021-10-16 12:00\",\"temp_c\":6.6,\"temp_f\":43.9,\"is_day\":1,\"condition\":{\"text\":\"Patchy rain possible\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/day/176.png\",\"code\":1063},\"wind_mph\":10.7,\"wind_kph\":17.3,\"wind_degree\":237,\"wind_dir\":\"WSW\",\"pressure_mb\":1017.0,\"pressure_in\":30.04,\"precip_mm\":0.1,\"precip_in\":0.0,\"humidity\":84,\"cloud\":100,\"feelslike_c\":3.4,\"feelslike_f\":38.1,\"windchill_c\":3.4,\"windchill_f\":38.1,\"heatindex_c\":6.6,\"heatindex_f\":43.9,\"dewpoint_c\":4.2,\"dewpoint_f\":39.6,\"will_it_rain\":1,\"chance_of_rain\":75,\"will_it_snow\":0,\"chance_of_snow\":0,\"vis_km\":10.0,\"vis_miles\":6.0,\"gust_mph\":13.9,\"gust_kph\":22.3,\"uv\":2.0},{\"time_epoch\":1634371200,\"time\":\"2021-10-16 13:00\",\"temp_c\":6.8,\"temp_f\":44.2,\"is_day\":1,\"condition\":{\"text\":\"Patchy rain possible\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/day/176.png\",\"code\":1063},\"wind_mph\":10.5,\"wind_kph\":16.9,\"wind_degree\":239,\"wind_dir\":\"WSW\",\"pressure_mb\":1018.0,\"pressure_in\":30.05,\"precip_mm\":0.1,\"precip_in\":0.0,\"humidity\":81,\"cloud\":100,\"feelslike_c\":3.7,\"feelslike_f\":38.7,\"windchill_c\":3.7,\"windchill_f\":38.7,\"heatindex_c\":6.8,\"heatindex_f\":44.2,\"dewpoint_c\":3.8,\"dewpoint_f\":38.8,\"will_it_rain\":1,\"chance_of_rain\":85,\"will_it_snow\":0,\"chance_of_snow\":0,\"vis_km\":10.0,\"vis_miles\":6.0,\"gust_mph\":13.9,\"gust_kph\":22.3,\"uv\":2.0},{\"time_epoch\":1634374800,\"time\":\"2021-10-16 14:00\",\"temp_c\":7.1,\"temp_f\":44.8,\"is_day\":1,\"condition\":{\"text\":\"Overcast\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/day/122.png\",\"code\":1009},\"wind_mph\":10.7,\"wind_kph\":17.3,\"wind_degree\":239,\"wind_dir\":\"WSW\",\"pressure_mb\":1018.0,\"pressure_in\":30.06,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":78,\"cloud\":100,\"feelslike_c\":4.0,\"feelslike_f\":39.2,\"windchill_c\":4.0,\"windchill_f\":39.2,\"heatindex_c\":7.1,\"heatindex_f\":44.8,\"dewpoint_c\":3.6,\"dewpoint_f\":38.5,\"will_it_rain\":0,\"chance_of_rain\":0,\"will_it_snow\":0,\"chance_of_snow\":0,\"vis_km\":10.0,\"vis_miles\":6.0,\"gust_mph\":13.9,\"gust_kph\":22.3,\"uv\":2.0},{\"time_epoch\":1634378400,\"time\":\"2021-10-16 15:00\",\"temp_c\":7.0,\"temp_f\":44.6,\"is_day\":1,\"condition\":{\"text\":\"Overcast\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/day/122.png\",\"code\":1009},\"wind_mph\":10.5,\"wind_kph\":16.9,\"wind_degree\":233,\"wind_dir\":\"SW\",\"pressure_mb\":1018.0,\"pressure_in\":30.06,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":78,\"cloud\":100,\"feelslike_c\":4.0,\"feelslike_f\":39.2,\"windchill_c\":4.0,\"windchill_f\":39.2,\"heatindex_c\":7.0,\"heatindex_f\":44.6,\"dewpoint_c\":3.4,\"dewpoint_f\":38.1,\"will_it_rain\":0,\"chance_of_rain\":0,\"will_it_snow\":0,\"chance_of_snow\":0,\"vis_km\":10.0,\"vis_miles\":6.0,\"gust_mph\":13.6,\"gust_kph\":22.0,\"uv\":2.0},{\"time_epoch\":1634382000,\"time\":\"2021-10-16 16:00\",\"temp_c\":7.6,\"temp_f\":45.7,\"is_day\":1,\"condition\":{\"text\":\"Patchy rain possible\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/day/176.png\",\"code\":1063},\"wind_mph\":9.6,\"wind_kph\":15.5,\"wind_degree\":232,\"wind_dir\": \"SW\",\"pressure_mb\":1018.0,\"pressure_in\":30.06,\"precip_mm\":0.1,\"precip_in\":0.0,\"humidity\":72,\"cloud\":71,\"feelslike_c\":4.9,\"feelslike_f\":40.8,\"windchill_c\":4.9,\"windchill_f\":40.8,\"heatindex_c\":7.6,\"heatindex_f\":45.7,\"dewpoint_c\":2.9,\"dewpoint_f\":37.2,\"will_it_rain\":1,\"chance_of_rain\":74,\"will_it_snow\":0,\"chance_of_snow\":0,\"vis_km\":10.0,\"vis_miles\":6.0,\"gust_mph\":12.3,\"gust_kph\":19.8,\"uv\":2.0},{\"time_epoch\":1634385600,\"time\":\"2021-10-16 17:00\",\"temp_c\":7.2,\"temp_f\":45.0,\"is_day\":1,\"condition\":{\"text\":\"Partly cloudy\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/day/116.png\",\"code\":1003},\"wind_mph\":7.4,\"wind_kph\":11.9,\"wind_degree\":227,\"wind_dir\":\"SW\",\"pressure_mb\":1018.0,\"pressure_in\":30.07,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":70,\"cloud\":56,\"feelslike_c\":4.9,\"feelslike_f\":40.8,\"windchill_c\":4.9,\"windchill_f\":40.8,\"heatindex_c\":7.2,\"heatindex_f\":45.0,\"dewpoint_c\":2.1,\"dewpoint_f\":35.8,\"will_it_rain\":0,\"chance_of_rain\":0,\"will_it_snow\":0,\"chance_of_snow\":0,\"vis_km\":10.0,\"vis_miles\":6.0,\"gust_mph\":11.0,\"gust_kph\":17.6,\"uv\":3.0},{\"time_epoch\":1634389200,\"time\":\"2021-10-16 18:00\",\"temp_c\":5.7,\"temp_f\":42.3,\"is_day\":1,\"condition\":{\"text\":\"Overcast\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/day/122.png\",\"code\":1009},\"wind_mph\":6.5,\"wind_kph\":10.4,\"wind_degree\":216,\"wind_dir\":\"SW\",\"pressure_mb\":1019.0,\"pressure_in\":30.08,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":73,\"cloud\":89,\"feelslike_c\":3.4,\"feelslike_f\":38.1,\"windchill_c\":3.4,\"windchill_f\":38.1,\"heatindex_c\":5.7,\"heatindex_f\":42.3,\"dewpoint_c\":1.3,\"dewpoint_f\":34.3,\"will_it_rain\":0,\"chance_of_rain\":0,\"will_it_snow\":0,\"chance_of_snow\":0,\"vis_km\":10.0,\"vis_miles\":6.0,\"gust_mph\":11.6,\"gust_kph\":18.7,\"uv\":2.0},{\"time_epoch\":1634392800,\"time\":\"2021-10-16 19:00\",\"temp_c\":6.6,\"temp_f\":43.9,\"is_day\":0,\"condition\":{\"text\":\"Overcast\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/night/122.png\",\"code\":1009},\"wind_mph\":7.4,\"wind_kph\":11.9,\"wind_degree\":221,\"wind_dir\":\"SW\",\"pressure_mb\":1019.0,\"pressure_in\":30.08,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":68,\"cloud\":95,\"feelslike_c\":4.2,\"feelslike_f\":39.6,\"windchill_c\":4.2,\"windchill_f\":39.6,\"heatindex_c\":6.6,\"heatindex_f\":43.9,\"dewpoint_c\":1.1,\"dewpoint_f\":34.0,\"will_it_rain\":0,\"chance_of_rain\":0,\"will_it_snow\":0,\"chance_of_snow\":0,\"vis_km\":10.0,\"vis_miles\":6.0,\"gust_mph\":11.2,\"gust_kph\":18.0,\"uv\":1.0},{\"time_epoch\":1634396400,\"time\":\"2021-10-16 20:00\",\"temp_c\":6.4,\"temp_f\":43.5,\"is_day\":0,\"condition\":{\"text\":\"Cloudy\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/night/119.png\",\"code\":1006},\"wind_mph\":6.7,\"wind_kph\":10.8,\"wind_degree\":212,\"wind_dir\":\"SSW\",\"pressure_mb\":1018.0,\"pressure_in\":30.07,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":67,\"cloud\":80,\"feelslike_c\":4.2,\"feelslike_f\":39.6,\"windchill_c\":4.2,\"windchill_f\":39.6,\"heatindex_c\":6.4,\"heatindex_f\":43.5,\"dewpoint_c\":0.6,\"dewpoint_f\":33.1,\"will_it_rain\":0,\"chance_of_rain\":0,\"will_it_snow\":0,\"chance_of_snow\":0,\"vis_km\":10.0,\"vis_miles\":6.0,\"gust_mph\":10.7,\"gust_kph\":17.3,\"uv\":1.0},{\"time_epoch\":1634400000,\"time\":\"2021-10-16 21:00\",\"temp_c\":6.5,\"temp_f\":43.7,\"is_day\":0,\"condition\":{\"text\":\"Overcast\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/night/122.png\",\"code\":1009},\"wind_mph\":7.6,\"wind_kph\":12.2,\"wind_degree\":216,\"wind_dir\":\"SW\",\"pressure_mb\":1018.0,\"pressure_in\":30.07,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":66,\"cloud\":100,\"feelslike_c\":4.0,\"feelslike_f\":39.2,\"windchill_c\":4.0,\"windchill_f\":39.2,\"heatindex_c\":6.5,\"heatindex_f\":43.7,\"dewpoint_c\":0.7,\"dewpoint_f\":33.3,\"will_it_rain\":0,\"chance_of_rain\":0,\"will_it_snow\":0,\"chance_of_snow\":0,\"vis_km\":10.0,\"vis_miles\":6.0,\"gust_mph\":11.9,\"gust_kph\":19.1,\"uv\":1.0},{\"time_epoch\":1634403600,\"time\":\"2021-10-16 22:00\",\"temp_c\":6.9,\"temp_f\":44.4,\"is_day\":0,\"condition\":{\"text\":\"Overcast\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/night/122.png\",\"code\":1009},\"wind_mph\":9.2,\"wind_kph\":14.8,\"wind_degree\":215,\"wind_dir\":\"SW\",\"pressure_mb\":1018.0,\"pressure_in\":30.06,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":65,\"cloud\":100,\"feelslike_c\":4.1,\"feelslike_f \":39.4,\"windchill_c\":4.1,\"windchill_f\":39.4,\"heatindex_c\":6.9,\"heatindex_f\":44.4,\"dewpoint_c\":0.8,\"dewpoint_f\":33.4,\"will_it_rain\":0,\"chance_of_rain\":0,\"will_it_snow\":0,\"chance_of_snow\":0,\"vis_km\":10.0,\"vis_miles\":6.0,\"gust_mph\":13.9,\"gust_kph\":22.3,\"uv\":1.0},{\"time_epoch\":1634407200,\"time\":\"2021-10-16 23:00\",\"temp_c\":7.0,\"temp_f\":44.6,\"is_day\":0,\"condition\":{\"text\":\"Partly cloudy\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/night/116.png\",\"code\":1003},\"wind_mph\":9.6,\"wind_kph\":15.5,\"wind_degree\":217,\"wind_dir\":\"SW\",\"pressure_mb\":1018.0,\"pressure_in\":30.06,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":65,\"cloud\":37,\"feelslike_c\":4.2,\"feelslike_f\":39.6,\"windchill_c\":4.2,\"windchill_f\":39.6,\"heatindex_c\":7.0,\"heatindex_f\":44.6,\"dewpoint_c\":0.9,\"dewpoint_f\":33.6,\"will_it_rain\":0,\"chance_of_rain\":0,\"will_it_snow\":0,\"chance_of_snow\":0,\"vis_km\":10.0,\"vis_miles\":6.0,\"gust_mph\":14.5,\"gust_kph\":23.4,\"uv\":1.0}]},{\"date\":\"2021-10-17\",\"date_epoch\":1634428800,\"day\":{\"maxtemp_c\":10.4,\"maxtemp_f\":50.7,\"mintemp_c\":5.3,\"mintemp_f\":41.5,\"avgtemp_c\":7.6,\"avgtemp_f\":45.7,\"maxwind_mph\":13.2,\"maxwind_kph\":21.2,\"totalprecip_mm\":0.2,\"totalprecip_in\":0.01,\"avgvis_km\":10.0,\"avgvis_miles\":6.0,\"avghumidity\":62.0,\"daily_will_it_rain\":1,\"daily_chance_of_rain\":89,\"daily_will_it_snow\":0,\"daily_chance_of_snow\":0,\"condition\":{\"text\":\"Patchy rain possible\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/day/176.png\",\"code\":1063},\"uv\":1.0},\"astro\":{\"sunrise\":\"07:55 AM\",\"sunset\":\"06:05 PM\",\"moonrise\":\"05:56 PM\",\"moonset\":\"03:07 AM\",\"moon_phase\":\"Full Moon\",\"moon_illumination\":\"83\"},\"hour\":[{\"time_epoch\":1634410800,\"time\":\"2021-10-17 00:00\",\"temp_c\":6.8,\"temp_f\":44.2,\"is_day\":0,\"condition\":{\"text\":\"Partly cloudy\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/night/116.png\",\"code\":1003},\"wind_mph\":10.1,\"wind_kph\":16.2,\"wind_degree\":218,\"wind_dir\":\"SW\",\"pressure_mb\":1017.0,\"pressure_in\":30.04,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":64,\"cloud\":42,\"feelslike_c\":3.8,\"feelslike_f\":38.8,\"windchill_c\":3.8,\"windchill_f\":38.8,\"heatindex_c\":6.8,\"heatindex_f\":44.2,\"dewpoint_c\":0.4,\"dewpoint_f\":32.7,\"will_it_rain\":0,\"chance_of_rain\":0,\"will_it_snow\":0,\"chance_of_snow\":0,\"vis_km\":10.0,\"vis_miles\":6.0,\"gust_mph\":15.7,\"gust_kph\":25.2,\"uv\":1.0},{\"time_epoch\":1634414400,\"time\":\"2021-10-17 01:00\",\"temp_c\":6.8,\"temp_f\":44.2,\"is_day\":0,\"condition\":{\"text\":\"Overcast\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/night/122.png\",\"code\":1009},\"wind_mph\":10.3,\"wind_kph\":16.6,\"wind_degree\":218,\"wind_dir\":\"SW\",\"pressure_mb\":1017.0,\"pressure_in\":30.03,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":63,\"cloud\":100,\"feelslike_c\":3.8,\"feelslike_f\":38.8,\"windchill_c\":3.8,\"windchill_f\":38.8,\"heatindex_c\":6.8,\"heatindex_f\":44.2,\"dewpoint_c\":0.2,\"dewpoint_f\":32.4,\"will_it_rain\":0,\"chance_of_rain\":0,\"will_it_snow\":0,\"chance_of_snow\":0,\"vis_km\":10.0,\"vis_miles\":6.0,\"gust_mph\":16.1,\"gust_kph\":25.9,\"uv\":1.0},{\"time_epoch\":1634418000,\"time\":\"2021-10-17 02:00\",\"temp_c\":6.9,\"temp_f\":44.4,\"is_day\":0,\"condition\":{\"text\":\"Overcast\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/night/122.png\",\"code\":1009},\"wind_mph\":9.8,\"wind_kph\":15.8,\"wind_degree\":215,\"wind_dir\":\"SW\",\"pressure_mb\":1017.0,\"pressure_in\":30.02,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":62,\"cloud\":100,\"feelslike_c\":4.0,\"feelslike_f\":39.2,\"windchill_c\":4.0,\"windchill_f\":39.2,\"heatindex_c\":6.9,\"heatindex_f\":44.4,\"dewpoint_c\":0.1,\"dewpoint_f\":32.2,\"will_it_rain\":0,\"chance_of_rain\":0,\"will_it_snow\":0,\"chance_of_snow\":0,\"vis_km\":10.0,\"vis_miles\":6.0,\"gust_mph\":15.2,\"gust_kph\":24.5,\"uv\":1.0},{\"time_epoch\":1634421600,\"time\":\"2021-10-17 03:00\",\"temp_c\":6.3,\"temp_f\":43.3,\"is_day\":0,\"condition\":{\"text\":\"Overcast\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/night/122.png\",\"code\":1009},\"wind_mph\":10.5,\"wind_kph\":16.9,\"wind_degree\":209,\"wind_dir\":\"SSW\",\"pressure_mb\":1017.0,\"pressure_in\":30.02,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":61,\"cloud\":92,\"feelslike_c\":3.1,\"feelslike_f\":37.6,\"windchill_c\":3.1,\"windchill_f\":37.6,\"heatindex_c\":6.3,\"heatindex_f\":43.3,\"dewpoint_c\":-0.6,\"dew point_f\":30.9,\"will_it_rain\":0,\"chance_of_rain\":0,\"will_it_snow\":0,\"chance_of_snow\":0,\"vis_km\":10.0,\"vis_miles\":6.0,\"gust_mph\":16.6,\"gust_kph\":26.6,\"uv\":1.0},{\"time_epoch\":1634425200,\"time\":\"2021-10-17 04:00\",\"temp_c\":5.9,\"temp_f\":42.6,\"is_day\":0,\"condition\":{\"text\":\"Overcast\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/night/122.png\",\"code\":1009},\"wind_mph\":9.6,\"wind_kph\":15.5,\"wind_degree\":204,\"wind_dir\":\"SSW\",\"pressure_mb\":1017.0,\"pressure_in\":30.02,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":62,\"cloud\":100,\"feelslike_c\":2.8,\"feelslike_f\":37.0,\"windchill_c\":2.8,\"windchill_f\":37.0,\"heatindex_c\":5.9,\"heatindex_f\":42.6,\"dewpoint_c\":-0.8,\"dewpoint_f\":30.6,\"will_it_rain\":0,\"chance_of_rain\":0,\"will_it_snow\":0,\"chance_of_snow\":0,\"vis_km\":10.0,\"vis_miles\":6.0,\"gust_mph\":15.2,\"gust_kph\":24.5,\"uv\":1.0},{\"time_epoch\":1634428800,\"time\":\"2021-10-17 05:00\",\"temp_c\":6.0,\"temp_f\":42.8,\"is_day\":0,\"condition\":{\"text\":\"Overcast\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/night/122.png\",\"code\":1009},\"wind_mph\":9.8,\"wind_kph\":15.8,\"wind_degree\":207,\"wind_dir\":\"SSW\",\"pressure_mb\":1016.0,\"pressure_in\":30.01,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":62,\"cloud\":100,\"feelslike_c\":2.9,\"feelslike_f\":37.2,\"windchill_c\":2.9,\"windchill_f\":37.2,\"heatindex_c\":6.0,\"heatindex_f\":42.8,\"dewpoint_c\":-0.7,\"dewpoint_f\":30.7,\"will_it_rain\":0,\"chance_of_rain\":0,\"will_it_snow\":0,\"chance_of_snow\":0,\"vis_km\":10.0,\"vis_miles\":6.0,\"gust_mph\":15.0,\"gust_kph\":24.1,\"uv\":1.0},{\"time_epoch\":1634432400,\"time\":\"2021-10-17 06:00\",\"temp_c\":5.8,\"temp_f\":42.4,\"is_day\":0,\"condition\":{\"text\":\"Overcast\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/night/122.png\",\"code\":1009},\"wind_mph\":9.8,\"wind_kph\":15.8,\"wind_degree\":205,\"wind_dir\":\"SSW\",\"pressure_mb\":1016.0,\"pressure_in\":30.01,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":62,\"cloud\":100,\"feelslike_c\":2.6,\"feelslike_f\":36.7,\"windchill_c\":2.6,\"windchill_f\":36.7,\"heatindex_c\":5.8,\"heatindex_f\":42.4,\"dewpoint_c\":-0.9,\"dewpoint_f\":30.4,\"will_it_rain\":0,\"chance_of_rain\":0,\"will_it_snow\":0,\"chance_of_snow\":0,\"vis_km\":10.0,\"vis_miles\":6.0,\"gust_mph\":15.0,\"gust_kph\":24.1,\"uv\":1.0},{\"time_epoch\":1634436000,\"time\":\"2021-10-17 07:00\",\"temp_c\":5.5,\"temp_f\":41.9,\"is_day\":0,\"condition\":{\"text\":\"Partly cloudy\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/night/116.png\",\"code\":1003},\"wind_mph\":9.6,\"wind_kph\":15.5,\"wind_degree\":201,\"wind_dir\":\"SSW\",\"pressure_mb\":1016.0,\"pressure_in\":30.0,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":63,\"cloud\":46,\"feelslike_c\":2.3,\"feelslike_f\":36.1,\"windchill_c\":2.3,\"windchill_f\":36.1,\"heatindex_c\":5.5,\"heatindex_f\":41.9,\"dewpoint_c\":-1.0,\"dewpoint_f\":30.2,\"will_it_rain\":0,\"chance_of_rain\":0,\"will_it_snow\":0,\"chance_of_snow\":0,\"vis_km\":10.0,\"vis_miles\":6.0,\"gust_mph\":14.5,\"gust_kph\":23.4,\"uv\":1.0},{\"time_epoch\":1634439600,\"time\":\"2021-10-17 08:00\",\"temp_c\":5.3,\"temp_f\":41.5,\"is_day\":1,\"condition\":{\"text\":\"Partly cloudy\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/day/116.png\",\"code\":1003},\"wind_mph\":9.8,\"wind_kph\":15.8,\"wind_degree\":201,\"wind_dir\":\"SSW\",\"pressure_mb\":1016.0,\"pressure_in\":29.99,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":63,\"cloud\":35,\"feelslike_c\":2.0,\"feelslike_f\":35.6,\"windchill_c\":2.0,\"windchill_f\":35.6,\"heatindex_c\":5.3,\"heatindex_f\":41.5,\"dewpoint_c\":-1.1,\"dewpoint_f\":30.0,\"will_it_rain\":0,\"chance_of_rain\":0,\"will_it_snow\":0,\"chance_of_snow\":0,\"vis_km\":10.0,\"vis_miles\":6.0,\"gust_mph\":15.2,\"gust_kph\":24.5,\"uv\":3.0},{\"time_epoch\":1634443200,\"time\":\"2021-10-17 09:00\",\"temp_c\":6.3,\"temp_f\":43.3,\"is_day\":1,\"condition\":{\"text\":\"Partly cloudy\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/day/116.png\",\"code\":1003},\"wind_mph\":10.7,\"wind_kph\":17.3,\"wind_degree\":206,\"wind_dir\":\"SSW\",\"pressure_mb\":1015.0,\"pressure_in\":29.98,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":60,\"cloud\":46,\"feelslike_c\":3.0,\"feelslike_f\":37.4,\"windchill_c\":3.0,\"windchill_f\":37.4,\"heatindex_c\":6.3,\"heatindex_f\":43.3,\"dewpoint_c\":-0.8,\"dewpoint_f\":30.6,\"will_it_rain\":0,\"chance_of_rain\":0,\"will_it_snow\":0,\"chance_of_snow\":0,\"vis_km\":10.0,\"vis_miles I/okhttp.OkHttpClient: \":6.0,\"gust_mph\":15.0,\"gust_kph\":24.1,\"uv\":3.0},{\"time_epoch\":1634446800,\"time\":\"2021-10-17 10:00\",\"temp_c\":7.2,\"temp_f\":45.0,\"is_day\":1,\"condition\":{\"text\":\"Sunny\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/day/113.png\",\"code\":1000},\"wind_mph\":12.1,\"wind_kph\":19.4,\"wind_degree\":209,\"wind_dir\":\"SSW\",\"pressure_mb\":1015.0,\"pressure_in\":29.97,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":57,\"cloud\":25,\"feelslike_c\":3.9,\"feelslike_f\":39.0,\"windchill_c\":3.9,\"windchill_f\":39.0,\"heatindex_c\":7.2,\"heatindex_f\":45.0,\"dewpoint_c\":-0.7,\"dewpoint_f\":30.7,\"will_it_rain\":0,\"chance_of_rain\":0,\"will_it_snow\":0,\"chance_of_snow\":0,\"vis_km\":10.0,\"vis_miles\":6.0,\"gust_mph\":15.9,\"gust_kph\":25.6,\"uv\":3.0},{\"time_epoch\":1634450400,\"time\":\"2021-10-17 11:00\",\"temp_c\":8.6,\"temp_f\":47.5,\"is_day\":1,\"condition\":{\"text\":\"Partly cloudy\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/day/116.png\",\"code\":1003},\"wind_mph\":12.8,\"wind_kph\":20.5,\"wind_degree\":213,\"wind_dir\":\"SSW\",\"pressure_mb\":1015.0,\"pressure_in\":29.96,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":54,\"cloud\":32,\"feelslike_c\":5.6,\"feelslike_f\":42.1,\"windchill_c\":5.6,\"windchill_f\":42.1,\"heatindex_c\":8.6,\"heatindex_f\":47.5,\"dewpoint_c\":-0.2,\"dewpoint_f\":31.6,\"will_it_rain\":0,\"chance_of_rain\":0,\"will_it_snow\":0,\"chance_of_snow\":0,\"vis_km\":10.0,\"vis_miles\":6.0,\"gust_mph\":14.8,\"gust_kph\":23.8,\"uv\":3.0},{\"time_epoch\":1634454000,\"time\":\"2021-10-17 12:00\",\"temp_c\":9.5,\"temp_f\":49.1,\"is_day\":1,\"condition\":{\"text\":\"Partly cloudy\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/day/116.png\",\"code\":1003},\"wind_mph\":13.2,\"wind_kph\":21.2,\"wind_degree\":218,\"wind_dir\":\"SW\",\"pressure_mb\":1014.0,\"pressure_in\":29.96,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":52,\"cloud\":38,\"feelslike_c\":6.6,\"feelslike_f\":43.9,\"windchill_c\":6.6,\"windchill_f\":43.9,\"heatindex_c\":9.5,\"heatindex_f\":49.1,\"dewpoint_c\":0.2,\"dewpoint_f\":32.4,\"will_it_rain\":0,\"chance_of_rain\":0,\"will_it_snow\":0,\"chance_of_snow\":0,\"vis_km\":10.0,\"vis_miles\":6.0,\"gust_mph\":15.2,\"gust_kph\":24.5,\"uv\":3.0},{\"time_epoch\":1634457600,\"time\":\"2021-10-17 13:00\",\"temp_c\":10.1,\"temp_f\":50.2,\"is_day\":1,\"condition\":{\"text\":\"Partly cloudy\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/day/116.png\",\"code\":1003},\"wind_mph\":13.0,\"wind_kph\":20.9,\"wind_degree\":221,\"wind_dir\":\"SW\",\"pressure_mb\":1014.0,\"pressure_in\":29.95,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":51,\"cloud\":50,\"feelslike_c\":7.4,\"feelslike_f\":45.3,\"windchill_c\":7.4,\"windchill_f\":45.3,\"heatindex_c\":10.1,\"heatindex_f\":50.2,\"dewpoint_c\":0.5,\"dewpoint_f\":32.9,\"will_it_rain\":0,\"chance_of_rain\":0,\"will_it_snow\":0,\"chance_of_snow\":0,\"vis_km\":10.0,\"vis_miles\":6.0,\"gust_mph\":15.0,\"gust_kph\":24.1,\"uv\":4.0},{\"time_epoch\":1634461200,\"time\":\"2021-10-17 14:00\",\"temp_c\":10.4,\"temp_f\":50.7,\"is_day\":1,\"condition\":{\"text\":\"Partly cloudy\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/day/116.png\",\"code\":1003},\"wind_mph\":12.8,\"wind_kph\":20.5,\"wind_degree\":223,\"wind_dir\":\"SW\",\"pressure_mb\":1014.0,\"pressure_in\":29.95,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":52,\"cloud\":47,\"feelslike_c\":7.8,\"feelslike_f\":46.0,\"windchill_c\":7.8,\"windchill_f\":46.0,\"heatindex_c\":10.4,\"heatindex_f\":50.7,\"dewpoint_c\":0.9,\"dewpoint_f\":33.6,\"will_it_rain\":0,\"chance_of_rain\":0,\"will_it_snow\":0,\"chance_of_snow\":0,\"vis_km\":10.0,\"vis_miles\":6.0,\"gust_mph\":14.8,\"gust_kph\":23.8,\"uv\":4.0},{\"time_epoch\":1634464800,\"time\":\"2021-10-17 15:00\",\"temp_c\":10.1,\"temp_f\":50.2,\"is_day\":1,\"condition\":{\"text\":\"Overcast\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/day/122.png\",\"code\":1009},\"wind_mph\":11.4,\"wind_kph\":18.4,\"wind_degree\":227,\"wind_dir\":\"SW\",\"pressure_mb\":1014.0,\"pressure_in\":29.95,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":53,\"cloud\":100,\"feelslike_c\":7.7,\"feelslike_f\":45.9,\"windchill_c\":7.7,\"windchill_f\":45.9,\"heatindex_c\":10.1,\"heatindex_f\":50.2,\"dewpoint_c\":1.1,\"dewpoint_f\":34.0,\"will_it_rain\":0,\"chance_of_rain\":0,\"will_it_snow\":0,\"chance_of_snow\":0,\"vis_km\":10.0,\"vis_miles\":6.0,\"gust_mph\":14.5,\"gust_kph\":23.4,\"uv\":3.0},{\"time_epoch\":1634468400,\"time\":\"2021-10-17 16:00\",\"temp_c\":9.6,\"t emp_f\":49.3,\"is_day\":1,\"condition\":{\"text\":\"Overcast\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/day/122.png\",\"code\":1009},\"wind_mph\":11.0,\"wind_kph\":17.6,\"wind_degree\":226,\"wind_dir\":\"SW\",\"pressure_mb\":1014.0,\"pressure_in\":29.94,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":57,\"cloud\":100,\"feelslike_c\":7.1,\"feelslike_f\":44.8,\"windchill_c\":7.1,\"windchill_f\":44.8,\"heatindex_c\":9.6,\"heatindex_f\":49.3,\"dewpoint_c\":1.6,\"dewpoint_f\":34.9,\"will_it_rain\":0,\"chance_of_rain\":0,\"will_it_snow\":0,\"chance_of_snow\":0,\"vis_km\":10.0,\"vis_miles\":6.0,\"gust_mph\":14.8,\"gust_kph\":23.8,\"uv\":2.0},{\"time_epoch\":1634472000,\"time\":\"2021-10-17 17:00\",\"temp_c\":9.4,\"temp_f\":48.9,\"is_day\":1,\"condition\":{\"text\":\"Partly cloudy\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/day/116.png\",\"code\":1003},\"wind_mph\":10.5,\"wind_kph\":16.9,\"wind_degree\":228,\"wind_dir\":\"SW\",\"pressure_mb\":1014.0,\"pressure_in\":29.95,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":62,\"cloud\":47,\"feelslike_c\":6.9,\"feelslike_f\":44.4,\"windchill_c\":6.9,\"windchill_f\":44.4,\"heatindex_c\":9.4,\"heatindex_f\":48.9,\"dewpoint_c\":2.4,\"dewpoint_f\":36.3,\"will_it_rain\":0,\"chance_of_rain\":0,\"will_it_snow\":0,\"chance_of_snow\":0,\"vis_km\":10.0,\"vis_miles\":6.0,\"gust_mph\":14.3,\"gust_kph\":23.0,\"uv\":3.0},{\"time_epoch\":1634475600,\"time\":\"2021-10-17 18:00\",\"temp_c\":8.5,\"temp_f\":47.3,\"is_day\":1,\"condition\":{\"text\":\"Overcast\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/day/122.png\",\"code\":1009},\"wind_mph\":9.2,\"wind_kph\":14.8,\"wind_degree\":228,\"wind_dir\":\"SW\",\"pressure_mb\":1014.0,\"pressure_in\":29.96,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":66,\"cloud\":100,\"feelslike_c\":6.1,\"feelslike_f\":43.0,\"windchill_c\":6.1,\"windchill_f\":43.0,\"heatindex_c\":8.5,\"heatindex_f\":47.3,\"dewpoint_c\":2.6,\"dewpoint_f\":36.7,\"will_it_rain\":0,\"chance_of_rain\":0,\"will_it_snow\":0,\"chance_of_snow\":0,\"vis_km\":10.0,\"vis_miles\":6.0,\"gust_mph\":13.9,\"gust_kph\":22.3,\"uv\":2.0},{\"time_epoch\":1634479200,\"time\":\"2021-10-17 19:00\",\"temp_c\":8.8,\"temp_f\":47.8,\"is_day\":0,\"condition\":{\"text\":\"Patchy rain possible\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/night/176.png\",\"code\":1063},\"wind_mph\":8.3,\"wind_kph\":13.3,\"wind_degree\":234,\"wind_dir\":\"SW\",\"pressure_mb\":1015.0,\"pressure_in\":29.96,\"precip_mm\":0.1,\"precip_in\":0.0,\"humidity\":67,\"cloud\":98,\"feelslike_c\":6.7,\"feelslike_f\":44.1,\"windchill_c\":6.7,\"windchill_f\":44.1,\"heatindex_c\":8.8,\"heatindex_f\":47.8,\"dewpoint_c\":3.0,\"dewpoint_f\":37.4,\"will_it_rain\":1,\"chance_of_rain\":77,\"will_it_snow\":0,\"chance_of_snow\":0,\"vis_km\":10.0,\"vis_miles\":6.0,\"gust_mph\":11.9,\"gust_kph\":19.1,\"uv\":1.0},{\"time_epoch\":1634482800,\"time\":\"2021-10-17 20:00\",\"temp_c\":7.8,\"temp_f\":46.0,\"is_day\":0,\"condition\":{\"text\":\"Cloudy\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/night/119.png\",\"code\":1006},\"wind_mph\":6.7,\"wind_kph\":10.8,\"wind_degree\":217,\"wind_dir\":\"SW\",\"pressure_mb\":1014.0,\"pressure_in\":29.96,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":71,\"cloud\":73,\"feelslike_c\":5.9,\"feelslike_f\":42.6,\"windchill_c\":5.9,\"windchill_f\":42.6,\"heatindex_c\":7.8,\"heatindex_f\":46.0,\"dewpoint_c\":2.9,\"dewpoint_f\":37.2,\"will_it_rain\":0,\"chance_of_rain\":0,\"will_it_snow\":0,\"chance_of_snow\":0,\"vis_km\":10.0,\"vis_miles\":6.0,\"gust_mph\":11.0,\"gust_kph\":17.6,\"uv\":1.0},{\"time_epoch\":1634486400,\"time\":\"2021-10-17 21:00\",\"temp_c\":7.5,\"temp_f\":45.5,\"is_day\":0,\"condition\":{\"text\":\"Clear\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/night/113.png\",\"code\":1000},\"wind_mph\":7.4,\"wind_kph\":11.9,\"wind_degree\":218,\"wind_dir\":\"SW\",\"pressure_mb\":1014.0,\"pressure_in\":29.95,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":72,\"cloud\":13,\"feelslike_c\":5.3,\"feelslike_f\":41.5,\"windchill_c\":5.3,\"windchill_f\":41.5,\"heatindex_c\":7.5,\"heatindex_f\":45.5,\"dewpoint_c\":2.8,\"dewpoint_f\":37.0,\"will_it_rain\":0,\"chance_of_rain\":0,\"will_it_snow\":0,\"chance_of_snow\":0,\"vis_km\":10.0,\"vis_miles\":6.0,\"gust_mph\":12.1,\"gust_kph\":19.4,\"uv\":1.0},{\"time_epoch\":1634490000,\"time\":\"2021-10-17 22:00\",\"temp_c\":6.9,\"temp_f\":44.4,\"is_day\":0,\"condition\":{\"text\":\"Patchy rain possible\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/night/176.png\",\" code\":1063},\"wind_mph\":7.4,\"wind_kph\":11.9,\"wind_degree\":216,\"wind_dir\":\"SW\",\"pressure_mb\":1014.0,\"pressure_in\":29.96,\"precip_mm\":0.1,\"precip_in\":0.0,\"humidity\":74,\"cloud\":80,\"feelslike_c\":4.6,\"feelslike_f\":40.3,\"windchill_c\":4.6,\"windchill_f\":40.3,\"heatindex_c\":6.9,\"heatindex_f\":44.4,\"dewpoint_c\":2.6,\"dewpoint_f\":36.7,\"will_it_rain\":1,\"chance_of_rain\":89,\"will_it_snow\":0,\"chance_of_snow\":0,\"vis_km\":10.0,\"vis_miles\":6.0,\"gust_mph\":12.3,\"gust_kph\":19.8,\"uv\":1.0},{\"time_epoch\":1634493600,\"time\":\"2021-10-17 23:00\",\"temp_c\":6.5,\"temp_f\":43.7,\"is_day\":0,\"condition\":{\"text\":\"Clear\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/night/113.png\",\"code\":1000},\"wind_mph\":7.4,\"wind_kph\":11.9,\"wind_degree\":214,\"wind_dir\":\"SW\",\"pressure_mb\":1014.0,\"pressure_in\":29.95,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":74,\"cloud\":13,\"feelslike_c\":4.1,\"feelslike_f\":39.4,\"windchill_c\":4.1,\"windchill_f\":39.4,\"heatindex_c\":6.5,\"heatindex_f\":43.7,\"dewpoint_c\":2.2,\"dewpoint_f\":36.0,\"will_it_rain\":0,\"chance_of_rain\":0,\"will_it_snow\":0,\"chance_of_snow\":0,\"vis_km\":10.0,\"vis_miles\":6.0,\"gust_mph\":12.5,\"gust_kph\":20.2,\"uv\":1.0}]}]}}"

val successForecastApiDomainObject = Forecast(
    icon = "Clear",
    temp = 5,
    wind = 11.9F,
    humidity = 65,
    hourly = listOf(
        HourlyForecast(
            icon = "Clear",
            time = LocalDateTime.parse("2021-10-16T00:00"),
            temp = 8
        ),
        HourlyForecast(
            icon = "Patchy rain possible",
            time = LocalDateTime.parse("2021-10-16T01:00"),
            temp = 7
        ),
        HourlyForecast(
            icon = "Partly cloudy",
            time = LocalDateTime.parse("2021-10-16T02:00"),
            temp = 6
        ),
        HourlyForecast(
            icon = "Overcast",
            time = LocalDateTime.parse("2021-10-16T03:00"),
            temp = 6
        ),
        HourlyForecast(
            icon = "Patchy rain possible",
            time = LocalDateTime.parse("2021-10-16T04:00"),
            temp = 7
        ),
        HourlyForecast(
            icon = "Cloudy",
            time = LocalDateTime.parse("2021-10-16T05:00"),
            temp = 7
        ),
        HourlyForecast(
            icon = "Overcast",
            time = LocalDateTime.parse("2021-10-16T06:00"),
            temp = 6
        ),
        HourlyForecast(
            icon = "Overcast",
            time = LocalDateTime.parse("2021-10-16T07:00"),
            temp = 6
        ),
        HourlyForecast(
            icon = "Overcast",
            time = LocalDateTime.parse("2021-10-16T08:00"),
            temp = 6
        ),
        HourlyForecast(
            icon = "Overcast",
            time = LocalDateTime.parse("2021-10-16T09:00"),
            temp = 5
        ),
        HourlyForecast(
            icon = "Patchy rain possible",
            time = LocalDateTime.parse("2021-10-16T10:00"),
            temp = 5
        ),
        HourlyForecast(
            icon = "Patchy rain possible",
            time = LocalDateTime.parse("2021-10-16T11:00"),
            temp = 6
        ),
        HourlyForecast(
            icon = "Patchy rain possible",
            time = LocalDateTime.parse("2021-10-16T12:00"),
            temp = 6
        ),
        HourlyForecast(
            icon = "Patchy rain possible",
            time = LocalDateTime.parse("2021-10-16T13:00"),
            temp = 6
        ),
        HourlyForecast(
            icon = "Overcast",
            time = LocalDateTime.parse("2021-10-16T14:00"),
            temp = 7
        ),
        HourlyForecast(
            icon = "Overcast",
            time = LocalDateTime.parse("2021-10-16T15:00"),
            temp = 7
        ),
        HourlyForecast(
            icon = "Patchy rain possible",
            time = LocalDateTime.parse("2021-10-16T16:00"),
            temp = 7
        ),
        HourlyForecast(
            icon = "Partly cloudy",
            time = LocalDateTime.parse("2021-10-16T17:00"),
            temp = 7
        ),
        HourlyForecast(
            icon = "Overcast",
            time = LocalDateTime.parse("2021-10-16T18:00"),
            temp = 5
        ),
        HourlyForecast(
            icon = "Overcast",
            time = LocalDateTime.parse("2021-10-16T19:00"),
            temp = 6
        ),
        HourlyForecast(
            icon = "Cloudy",
            time = LocalDateTime.parse("2021-10-16T20:00"),
            temp = 6
        ),
        HourlyForecast(
            icon = "Overcast",
            time = LocalDateTime.parse("2021-10-16T21:00"),
            temp = 6
        ),
        HourlyForecast(
            icon = "Overcast",
            time = LocalDateTime.parse("2021-10-16T22:00"),
            temp = 6
        ),
        HourlyForecast(
            icon = "Partly cloudy",
            time = LocalDateTime.parse("2021-10-16T23:00"),
            temp = 7
        ),
        HourlyForecast(
            icon = "Partly cloudy",
            time = LocalDateTime.parse("2021-10-17T00:00"),
            temp = 6
        ),
        HourlyForecast(
            icon = "Overcast",
            time = LocalDateTime.parse("2021-10-17T01:00"),
            temp = 6
        ),
        HourlyForecast(
            icon = "Overcast",
            time = LocalDateTime.parse("2021-10-17T02:00"),
            temp = 6
        ),
        HourlyForecast(
            icon = "Overcast",
            time = LocalDateTime.parse("2021-10-17T03:00"),
            temp = 6
        ),
        HourlyForecast(
            icon = "Overcast",
            time = LocalDateTime.parse("2021-10-17T04:00"),
            temp = 5
        ),
        HourlyForecast(
            icon = "Overcast",
            time = LocalDateTime.parse("2021-10-17T05:00"),
            temp = 6
        ),
        HourlyForecast(
            icon = "Overcast",
            time = LocalDateTime.parse("2021-10-17T06:00"),
            temp = 5
        ),
        HourlyForecast(
            icon = "Partly cloudy",
            time = LocalDateTime.parse("2021-10-17T07:00"),
            temp = 5
        ),
        HourlyForecast(
            icon = "Partly cloudy",
            time = LocalDateTime.parse("2021-10-17T08:00"),
            temp = 5
        ),
        HourlyForecast(
            icon = "Partly cloudy",
            time = LocalDateTime.parse("2021-10-17T09:00"),
            temp = 6
        ),
        HourlyForecast(
            icon = "Sunny",
            time = LocalDateTime.parse("2021-10-17T10:00"),
            temp = 7
        ),
        HourlyForecast(
            icon = "Partly cloudy",
            time = LocalDateTime.parse("2021-10-17T11:00"),
            temp = 8
        ),
        HourlyForecast(
            icon = "Partly cloudy",
            time = LocalDateTime.parse("2021-10-17T12:00"),
            temp = 9
        ),
        HourlyForecast(
            icon = "Partly cloudy",
            time = LocalDateTime.parse("2021-10-17T13:00"),
            temp = 10
        ),
        HourlyForecast(
            icon = "Partly cloudy",
            time = LocalDateTime.parse("2021-10-17T14:00"),
            temp = 10
        ),
        HourlyForecast(
            icon = "Overcast",
            time = LocalDateTime.parse("2021-10-17T15:00"),
            temp = 10
        ),
        HourlyForecast(
            icon = "Overcast",
            time = LocalDateTime.parse("2021-10-17T16:00"),
            temp = 9
        ),
        HourlyForecast(
            icon = "Partly cloudy",
            time = LocalDateTime.parse("2021-10-17T17:00"),
            temp = 9
        ),
        HourlyForecast(
            icon = "Overcast",
            time = LocalDateTime.parse("2021-10-17T18:00"),
            temp = 8
        ),
        HourlyForecast(
            icon = "Patchy rain possible",
            time = LocalDateTime.parse("2021-10-17T19:00"),
            temp = 8
        ),
        HourlyForecast(
            icon = "Cloudy",
            time = LocalDateTime.parse("2021-10-17T20:00"),
            temp = 7
        ),
        HourlyForecast(
            icon = "Clear",
            time = LocalDateTime.parse("2021-10-17T21:00"),
            temp = 7
        ),
        HourlyForecast(
            icon = "Patchy rain possible",
            time = LocalDateTime.parse("2021-10-17T22:00"),
            temp = 6
        ),
        HourlyForecast(icon = "Clear", time = LocalDateTime.parse("2021-10-17T23:00"), temp = 6),
    )
)
