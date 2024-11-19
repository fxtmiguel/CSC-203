def count(s: str, c: str) -> int:
    sum = 0
    for i in range(len(s)):
        if s[i] == c:
            sum += 1
    return sum

if __name__ == '__main__':
    # Numerics
    x = 2
    y = 4.0
    z = x ** y // 3

    print(f'x: {x}')
    print(f'y: {y}')
    print(f'z: {z}')

    # Fixed size list
    a = [1, 2, 3, 4]
    for n in a:
        print(n, end=' ')
    print()

    # String
    b = 'mochimochimochi'
    found = count(b, "o")
    print(found)

    # Variable sized list
    c = []
    c.append(6.5)
    c.append(4.3)
    c.append(2.1)
    for i in range(len(c)):
        print(f"{i}: {c[i]}")