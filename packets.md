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

## Move to position

### ID: `1`

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
            <td>Double</td>
            <td>X position of target</td>
        </tr>
        <tr>
            <td>Double</td>
            <td>Y position of target</td>
        </tr>
    </tbody>
</table>

# Clientbound Packets

## Spawn entity

### ID: `1`

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
            <td>Integer</td>
            <td>Entity type id</td>
        </tr>
        <tr>
            <td>Integer</td>
            <td>Entity instance id</td>
        </tr>
        <tr>
            <td>Double</td>
            <td>X position of entity</td>
        </tr>
        <tr>
            <td>Double</td>
            <td>Y position of entity</td>
        </tr>
        <tr>
            <td>Double</td>
            <td>Height position of entity</td>
        </tr>
        <tr>
            <td>Integer</td>
            <td>Length of extra data</td>
        </tr>
        <tr>
            <td>Byte[]</td>
            <td>Extra data</td>
        </tr>
    </tbody>
</table>

## Despawn entity

### ID: `2`
<table>
    <thead>
        <tr>
            <th>Type</th>
            <th>Description</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>Integer</td>
            <td>Entity instance id</td>
        </tr>
    </tbody>
</table>

## Update entity

### ID: `3`
<table>
    <thead>
        <tr>
            <th>Type</th>
            <th>Description</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>Integer</td>
            <td>Entity instance id</td>
        </tr>
        <tr>
            <td>Integer</td>
            <td>Length of extra data</td>
        </tr>
        <tr>
            <td>Byte[]</td>
            <td>Extra data</td>
        </tr>
    </tbody>
</table>

## Move entity

### ID: `4`
<table>
    <thead>
        <tr>
            <th>Type</th>
            <th>Description</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>Integer</td>
            <td>Entity instance id</td>
        </tr>
        <tr>
            <td>Double</td>
            <td>X position of entity</td>
        </tr>
        <tr>
            <td>Double</td>
            <td>Y position of entity</td>
        </tr>
        <tr>
            <td>Double</td>
            <td>Height position of entity</td>
        </tr>
    </tbody>
</table>
