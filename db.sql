CREATE TABLE `adresse` (
                           `id` int(11) NOT NULL,
                           `num_rue` int(11) DEFAULT NULL,
                           `nom_rue` varchar(255) DEFAULT NULL,
                           `code_postal` varchar(255) DEFAULT NULL,
                           `ville` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE `annexe` (
                          `id` int(11) NOT NULL,
                          `num_bien` int(11) DEFAULT NULL,
                          `surface` int(11) DEFAULT NULL,
                          `num_annexe` int(11) DEFAULT NULL,
                          `nb_piece` int(11) DEFAULT NULL,
                          `description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE `bien` (
                        `id` int(11) NOT NULL,
                        `date_creation` date DEFAULT NULL,
                        `surface` int(11) DEFAULT NULL,
                        `nbPiece` int(11) DEFAULT NULL,
                        `type_eau_chaude` varchar(255) DEFAULT NULL,
                        `chauffage` varchar(255) DEFAULT NULL,
                        `type_bien` varchar(255) DEFAULT NULL,
                        `id_adresse` int(11) DEFAULT NULL,
                        `classification` varchar(255) DEFAULT NULL,
                        `etage` int(11) DEFAULT NULL,
                        `num_logement` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE `mobilier` (
                            `id` int(11) NOT NULL,
                            `id_piece` int(11) DEFAULT NULL,
                            `description` varchar(255) DEFAULT NULL,
                            `nature` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



CREATE TABLE `piece` (
                         `id` int(11) NOT NULL,
                         `num_bien` int(11) DEFAULT NULL,
                         `id_affectation` int(11) DEFAULT NULL,
                         `description` varchar(255) DEFAULT NULL,
                         `surface` int(11) DEFAULT NULL,
                         `nb_murs` int(11) DEFAULT NULL,
                         `nb_portes` int(11) DEFAULT NULL,
                         `nb_fenetre` int(11) DEFAULT NULL,
                         `affectation_piece` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



ALTER TABLE `adresse`
    ADD PRIMARY KEY (`id`);


ALTER TABLE `annexe`
    ADD PRIMARY KEY (`id`),
    ADD KEY `num_bien` (`num_bien`);


ALTER TABLE `bien`
    ADD PRIMARY KEY (`id`),
    ADD KEY `id_adresse` (`id_adresse`);

ALTER TABLE `mobilier`
    ADD PRIMARY KEY (`id`),
    ADD KEY `id_piece` (`id_piece`);

ALTER TABLE `piece`
    ADD PRIMARY KEY (`id`),
    ADD KEY `num_bien` (`num_bien`);



ALTER TABLE `adresse`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=54;

ALTER TABLE `annexe`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `bien`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

ALTER TABLE `mobilier`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `piece`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `annexe`
    ADD CONSTRAINT `annexe_ibfk_1` FOREIGN KEY (`num_bien`) REFERENCES `bien` (`id`);

ALTER TABLE `bien`
    ADD CONSTRAINT `bien_ibfk_1` FOREIGN KEY (`id_adresse`) REFERENCES `adresse` (`id`);

ALTER TABLE `mobilier`
    ADD CONSTRAINT `mobilier_ibfk_1` FOREIGN KEY (`id_piece`) REFERENCES `piece` (`id`);

ALTER TABLE `piece`
    ADD CONSTRAINT `piece_ibfk_1` FOREIGN KEY (`num_bien`) REFERENCES `bien` (`id`);
COMMIT;
