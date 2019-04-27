package com.knowledgespike.scores.service

import com.knowledgespike.scores.repository.IScoresRepository

interface IScoresService {

}

class ScoresService(val scoresRepository: IScoresRepository) : IScoresService {

}