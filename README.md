# stockeR
Currently only works with Komplett.no and has to be connected to your own Discord bot.
How To stockeR:
1. Create your own discord bot at https://discord.com/developers/applications/ and add it to your own channel.
2. Add your bot token to stockeR.java > DiscordApi api = new DiscordApiBuilder().setToken("YOURTOKENHERE").login().join();
3. Add pages to check using: urls.add("komplett.no/. . .");
4. Run program.
5. Use !ping in any discord channel connected to the bot to start the scanning.

The target goal is to have the program run and check stock consistently by itself and posting finds in a channel that is userdefined.
