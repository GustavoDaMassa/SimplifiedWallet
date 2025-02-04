DELETE FROM TRANSACTIONS;

DELETE FROM WALLETS;

INSERT TO
    WALLETS(
        ID , FULL_NAME, CPF, EMAIL, "PASSWORD", "TYPE", BALANCE
    )
    VALUES(
        1, ' Gustavo - User ', 12012012012, 'gustavo@test.com', '12345678', 1, 3000.00
    )

INSERT TO
    WALLETS(
        ID , FULL_NAME, CPF, EMAIL, "PASSWORD", "TYPE", BALANCE
    )
    VALUES(
        2, ' henrique - Lojista ', 12012012013, 'henrique@test.com', '12345678', 2, 3000.00
    )