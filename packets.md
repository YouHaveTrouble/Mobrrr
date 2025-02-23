All Strings are UTF-8 encoded.

Packet IDs are 1 byte long.

# Serverbound Packets

## Login

### ID: `0`

### Content

<table>
    <thead>
        <tr>
            <th>Type</th>
            <th>Description</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>Short</td>
            <td>Length of the token</td>
        </tr>
        <tr>
            <td>String</td>
            <td>Token</td>
        </tr>
    </tbody>

</table>

# Clientbound Packets
