# EntityManager

`EntityManager` is a Minecraft plugin designed to manage and filter entities within your Minecraft server. It provides functionality for both whitelisting and blacklisting entities, giving server administrators control over which entities should be allowed or removed from the game world.

## Features

- **Entity Filtering**: Configure which entities should be allowed or removed based on your preferences.
- **Flexible Configuration**: Control filtering of non-mob entities like dropped items, players, arrows, fireballs, and more.
- **Whitelist and Blacklist Modes**: Easily switch between whitelist and blacklist modes via configuration.
- **Compatibility**: Supports a range of Minecraft versions due to the removal of the API version from `plugin.yml`.

## Installation

1. Download the latest release of `EntityManager` from the [releases page](https://github.com/appztr4ckt/entitymanager/releases).
2. Place the `EntityManager.jar` file into the `plugins` directory of your Minecraft server.
3. Restart the server to generate the configuration files.

## Configuration

`EntityManager` uses several configuration files to manage entity filtering:

- **`config.yml`**: Main configuration file to enable or disable filtering for non-mob entities.
- **`whitelist.yml`**: Contains a list of entities to keep when whitelist mode is enabled.
- **`blacklist.yml`**: Contains a list of entities to remove when blacklist mode is enabled.

### Configuration Options

- **`turn-blacklist-to-whitelist`**: Set to `true` to use the list in `whitelist.yml` as a whitelist. Set to `false` to use the list in `blacklist.yml` as a blacklist.
- **`check-interval`**: Interval in ticks (20 ticks = 1 second) for how often the plugin checks and removes unlisted entities.
- **Non-Mob Entity Settings**: Configure whether to remove specific non-mob entities such as dropped items, players, arrows, fireballs, experience orbs, and tridents.

## Commands

There are currently no commands for `EntityManager`. All configurations are handled through the configuration files.

## Permissions

No permissions are required to use `EntityManager`. It operates based on the settings defined in the configuration files.

## Troubleshooting

- **Entities Not Being Filtered**: Ensure that the correct entities are listed in `whitelist.yml` or `blacklist.yml` according to your chosen mode.
- **Warnings About Invalid EntityTypes**: Verify that your configuration does not contain invalid or unsupported entity types.

## Support

For support, please open an issue on the [GitHub Issues page](https://github.com/appztr4ckt/entitymanager/issues). Provide details about the problem and any relevant logs.

## Credits

- **Development**: [Appztr4ckt](https://github.com/appztr4ckt)
- **Testing and Feedback**: Thank you to the Minecraft community for feedback and testing.

---

This `README.md` provides a comprehensive overview of the `EntityManager` plugin, including its features, installation instructions, configuration details, and troubleshooting tips. Be sure to replace placeholders like `yourusername` with actual values relevant to your project.
