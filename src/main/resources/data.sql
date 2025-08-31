-- Rolleri başlangıçta eklemek için (yoksa ekler)
INSERT INTO bank.role(authority) SELECT 'ROLE_USER'
WHERE NOT EXISTS (SELECT 1 FROM bank.role WHERE authority='ROLE_USER');

INSERT INTO bank.role(authority) SELECT 'ROLE_ADMIN'
WHERE NOT EXISTS (SELECT 1 FROM bank.role WHERE authority='ROLE_ADMIN');
